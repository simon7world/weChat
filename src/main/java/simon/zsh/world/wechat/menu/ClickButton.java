package simon.zsh.world.wechat.menu;

import simon.zsh.world.wechat.basis.ButtonBase;

/**
 * 点击类型的按钮
 */
public class ClickButton extends ButtonBase {

	private String type;

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(final String key) {
		this.key = key;
	}
}