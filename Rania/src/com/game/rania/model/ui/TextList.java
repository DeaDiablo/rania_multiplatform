package com.game.rania.model.ui;

import java.util.Vector;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.rania.model.MultilineText;
import com.game.rania.model.element.HUDObject;
import com.game.rania.model.element.RegionID;

public class TextList extends HUDObject{
	
	protected class TextLine{
		public TextLine(String text, Color color){
			this.text  = text;
			this.color = color;
		}
		public String text;
		public Color  color; 
	}
	
	protected MultilineText text = null;
	protected Vector<TextLine> lines = new Vector<TextLine>();
	protected float widthList = 0.0f;
	protected float heightList = 0.0f;

	protected int countLine = 0;
	protected int beginLine = 0;
	protected int endLine = 0;

	public TextList(RegionID idTexture, float x, float y, MultilineText text, float widthList, float heightList){
		super(idTexture, x, y);
		this.text = text;
		this.widthList = widthList;
		this.heightList = heightList;
		touchObject = true;
		countLine = (int) (heightList / text.font.getLineHeight());
		parseText(lines, text.content, text.color);
	}
	
	public void addText(String text){
		parseText(lines, text, this.text.color);
		goToEnd();
	}
	
	public void addText(String text, Color color){
		parseText(lines, text, color);
		goToEnd();
	}
	
	protected String bufferText;
	
	protected void goToEnd(){
		endLine = lines.size();
		beginLine = Math.max(0, endLine - countLine);
	}
	
	protected void parseText(Vector<TextLine> lines, String text, Color color){
		if (text.isEmpty())
			return;

		float textWidth = this.text.getTextBound(text).width;
		do {
			if (textWidth < widthList)	{
				lines.add(new TextLine(text + "\n", color));
				break;
			}

			int divIndex = 0;
			while(divIndex < text.length() &&
				  this.text.getTextBound(text.substring(0, divIndex)).width < widthList){
				divIndex++;
			}

			divIndex--;
			if(divIndex < 0)
				break;

			int indexSpace = text.substring(0, divIndex).lastIndexOf(" ");
			if (indexSpace >= 0)
				divIndex = indexSpace;
				
			lines.add(new TextLine(text.substring(0, divIndex) + "\n", color));
			text = text.substring(divIndex + 1);
			textWidth = this.text.getTextBound(text).width;
		} while(textWidth > 0);
	}
	
	@Override
	public boolean draw(SpriteBatch sprite){		
		if (!visible)
			return false;
		
		sprite.setColor(color);
		drawRegion(sprite, region);
		
		if (text != null){
			for (int i = beginLine; i < endLine; i++) {
				TextLine line = lines.get(i);
				if (line != null)
				{
					text.content = line.text;
					text.color = line.color;
					text.draw(sprite, text.position.x + position.x, text.position.y + position.y - (i - beginLine) * text.font.getLineHeight(), angle, scale.x, scale.y);
				}
			}
		}			
		
		return true;
	}
	
	protected float   dragPosY  = 0.0f;
	protected boolean startDrag = false;
	
	
	@Override
	public boolean touchDown(float x, float y) {
		setFocus();
		if (lines.size() > countLine) {
			dragPosY = y;
			startDrag = true;
		}
		return true;
	}

	protected static final float speedDrag = 1.0f;
	
	@Override
	public boolean touchDragged(float x, float y) {
		if(!startDrag)
			return true;
		
		int len   = lines.size();
		int delta = (int) ((y - dragPosY) * speedDrag * 0.1f);
		dragPosY = y;
		if (delta != 0) {
			beginLine += delta;
			if (beginLine < 0)
				beginLine = 0;
			if (beginLine > len - countLine)
				beginLine = len - countLine;
			
			if (len < beginLine + countLine)
				endLine = len;
			else
				endLine = beginLine + countLine;
		}		
		
		return true;
	}
	
	@Override
	public boolean touchUp(float x, float y) {
		startDrag = false;
		return true;
	}
	
	public void setFocus(){
		FocusElement.setFocus(this);
	}
	
}
