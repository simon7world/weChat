package simon.zsh.world.wechat.menu;

import simon.zsh.world.wechat.basis.ButtonBase;

/**
 * 视图类型的按钮
 */
public final class ViewButton extends ButtonBase {

	public ViewButton(final String name) {

		super(name);
		type = MenuTypes.VIEW;
	}

	/**
	 * 按钮类型
	 */
	private String type;

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	/**
	 * 按钮链接
	 */
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}
}
