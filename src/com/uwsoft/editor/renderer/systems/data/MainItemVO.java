package com.uwsoft.editor.renderer.systems.data;

import com.badlogic.ashley.core.Entity;
import com.uwsoft.editor.renderer.components.MainItemComponent;
import com.uwsoft.editor.renderer.components.PolygonComponent;
import com.uwsoft.editor.renderer.components.ShaderComponent;
import com.uwsoft.editor.renderer.components.TintComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.ZIndexComponent;
import com.uwsoft.editor.renderer.components.physics.PhysicsBodyComponent;

import java.util.Arrays;


public class MainItemVO {
    public int uniqueId = -1;
    public String itemIdentifier = "";
    public String itemName = "";
    public String[] tags = null;
    public String customVars = "";
    public float x;
    public float y;
    public float scaleX = 1f;
    public float scaleY = 1f;
    public float originX = Float.NaN;
    public float originY = Float.NaN;
    public float rotation;
    public int zIndex = 0;
    public String layerName = "";
    public float[] tint = {1, 1, 1, 1};

    public String shaderName = "";

    public ShapeVO shape = null;
    public com.uwsoft.editor.renderer.systems.data.PhysicsBodyDataVO physics = null;

    public MainItemVO() {

    }

    public MainItemVO(MainItemVO vo) {
        uniqueId = vo.uniqueId;
        itemIdentifier = new String(vo.itemIdentifier);
        itemName = new String(vo.itemName);
        if (vo.tags != null) tags = Arrays.copyOf(vo.tags, vo.tags.length);
        customVars = new String(vo.customVars);
        x = vo.x;
        y = vo.y;
        rotation = vo.rotation;
        zIndex = vo.zIndex;
        layerName = new String(vo.layerName);
        if (vo.tint != null) tint = Arrays.copyOf(vo.tint, vo.tint.length);
        scaleX = vo.scaleX;
        scaleY = vo.scaleY;
        originX = vo.originX;
        originY = vo.originY;

        if (vo.shape != null) {
            shape = vo.shape.clone();
        }

        if (vo.physics != null) {
            physics = new com.uwsoft.editor.renderer.systems.data.PhysicsBodyDataVO(vo.physics);
        }
    }

    public void loadFromEntity(Entity entity) {
        MainItemComponent mainItemComponent = entity.getComponent(MainItemComponent.class);
        TransformComponent transformComponent = entity.getComponent(TransformComponent.class);
        TintComponent tintComponent = entity.getComponent(TintComponent.class);
        ZIndexComponent zindexComponent = entity.getComponent(ZIndexComponent.class);

        uniqueId = mainItemComponent.uniqueId;
        itemIdentifier = mainItemComponent.itemIdentifier;
        itemName = mainItemComponent.libraryLink;
        tags = new String[mainItemComponent.tags.size()];
        tags = mainItemComponent.tags.toArray(tags);
        customVars = mainItemComponent.customVars;

        x = transformComponent.x;
        y = transformComponent.y;
        scaleX = transformComponent.scaleX;
        scaleY = transformComponent.scaleY;
        originX = transformComponent.originX;
        originY = transformComponent.originY;
        rotation = transformComponent.rotation;

        layerName = zindexComponent.layerName;

        tint = new float[4];
        tint[0] = tintComponent.color.r;
        tint[1] = tintComponent.color.g;
        tint[2] = tintComponent.color.b;
        tint[3] = tintComponent.color.a;

        zIndex = zindexComponent.getZIndex();

        /**
         * Secondary components
         */
        PolygonComponent polygonComponent = entity.getComponent(PolygonComponent.class);
        if (polygonComponent != null && polygonComponent.vertices != null) {
            shape = new ShapeVO();
            shape.polygons = polygonComponent.vertices;
        }
        PhysicsBodyComponent physicsComponent = entity.getComponent(PhysicsBodyComponent.class);
        if (physicsComponent != null) {
            physics = new com.uwsoft.editor.renderer.systems.data.PhysicsBodyDataVO();
            physics.loadFromComponent(physicsComponent);
        }

        ShaderComponent shaderComponent = entity.getComponent(ShaderComponent.class);
        if (shaderComponent != null && shaderComponent.shaderName != null) {
            shaderName = shaderComponent.shaderName;
        }
    }
}
