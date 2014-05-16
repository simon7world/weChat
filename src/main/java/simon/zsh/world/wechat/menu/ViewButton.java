package simon.zsh.world.wechat.menu;

import simon.zsh.world.wechat.Types;
import simon.zsh.world.wechat.basis.ButtonBase;

/**
 * 视图类型的按钮
 */
public final class ViewButton extends ButtonBase {

	public ViewButton() {

		setType(Types.EVENT_VIEW);
	}

	private String type;

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}
}
