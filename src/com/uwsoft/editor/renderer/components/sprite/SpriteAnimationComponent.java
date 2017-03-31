package com.uwsoft.editor.renderer.components.sprite;

import java.util.HashMap;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.uwsoft.editor.renderer.systems.data.FrameRange;

public class SpriteAnimationComponent implements Component {
	public String animationName = "";
	public int fps = 24;
	public HashMap<String, FrameRange> frameRangeMap = new HashMap<String, FrameRange>();
    public String currentAnimation;
    public Animation.PlayMode playMode = Animation.PlayMode.LOOP;
	
}
