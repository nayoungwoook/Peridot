package dev.nayoungwook.peridot.renderer;

import java.awt.Color;

import org.json.JSONObject;

import dev.nayoungwook.peridot.object.Sprite;
import dev.nayoungwook.peridot.object.font.Font;

public class Renderer {

	public static final void image(Sprite sprite, String conf) {
		JSONObject obj = new JSONObject(conf);

		boolean ui = false;
		if (obj.has("ui"))
			ui = obj.getBoolean("ui");

		ImageQueue iq = new ImageQueue(sprite, obj.getInt("x"), obj.getInt("y"), obj.getInt("width"),
				obj.getInt("height"), ui);

		if (obj.has("z"))
			iq.position.z = obj.getFloat("z");
		if (obj.has("rotation"))
			iq.rotation = obj.getFloat("rotation");
		if (obj.has("flipx"))
			iq.flipx = obj.getBoolean("flipx");
		if (obj.has("flipy"))
			iq.flipy = obj.getBoolean("flipy");
		if (obj.has("visible"))
			iq.visible = obj.getBoolean("visible");
		if (obj.has("anchorx"))
			iq.anchor.x = obj.getFloat("anchorx");
		if (obj.has("anchory"))
			iq.anchor.y = obj.getFloat("anchory");

		iq.render();
	}

	public static final void rect(Color c, String conf) {
		JSONObject obj = new JSONObject(conf);

		boolean ui = false;
		if (obj.has("ui"))
			ui = obj.getBoolean("ui");

		RectQueue rq = new RectQueue(obj.getInt("x"), obj.getInt("y"), obj.getInt("width"), obj.getInt("height"), ui);
		rq.color = c;

		if (obj.has("z"))
			rq.position.z = obj.getFloat("z");
		if (obj.has("rotation"))
			rq.rotation = obj.getFloat("rotation");
		if (obj.has("flipx"))
			rq.flipx = obj.getBoolean("flipx");
		if (obj.has("flipy"))
			rq.flipy = obj.getBoolean("flipy");
		if (obj.has("visible"))
			rq.visible = obj.getBoolean("visible");
		if (obj.has("anchorx"))
			rq.anchor.x = obj.getFloat("anchorx");
		if (obj.has("anchory"))
			rq.anchor.y = obj.getFloat("anchory");

		rq.render();
	}

	public static final void text(String str, Color c, Font f, String conf) {
		JSONObject obj = new JSONObject(conf);

		boolean ui = false;
		if (obj.has("ui"))
			ui = obj.getBoolean("ui");

		TextQueue tq = new TextQueue(str, obj.getInt("x"), obj.getInt("y"), f, ui);
		tq.color = c;
		tq.width = 0;
		tq.height = 0;

		if (obj.has("align"))
			tq.setTextAlign(obj.getString("align"));

		if (obj.has("z"))
			tq.position.z = obj.getFloat("z");
		if (obj.has("rotation"))
			tq.rotation = obj.getFloat("rotation");
		if (obj.has("flipx"))
			tq.flipx = obj.getBoolean("flipx");
		if (obj.has("flipy"))
			tq.flipy = obj.getBoolean("flipy");
		if (obj.has("visible"))
			tq.visible = obj.getBoolean("visible");
		if (obj.has("anchorx"))
			tq.anchor.x = obj.getFloat("anchorx");
		if (obj.has("anchory"))
			tq.anchor.y = obj.getFloat("anchory");

		tq.render();
	}

}
