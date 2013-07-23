package com.game.rania.utils;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class DrawUtils {

	public static void drawDottedCircle(ShapeRenderer shapeRanderer, float x,
			float y, float radius, float stepDegrees) {
		float step = stepDegrees * MathUtils.PI / 180.0f;
		for (float angle = 0.0f; angle < 2 * MathUtils.PI; angle += 2 * step) {
			shapeRanderer.line(x + radius * MathUtils.cos(angle), y + radius
					* MathUtils.sin(angle),
					x + radius * MathUtils.cos(angle + step), y + radius
							* MathUtils.sin(angle + step));
		}

	}
}
