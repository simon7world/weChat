package simon.zsh.world.wechat.menu;

import simon.zsh.world.wechat.Types;
import simon.zsh.world.wechat.basis.ButtonBase;

/**
 * 点击类型的按钮
 */
public final class ClickButton extends ButtonBase {

	public ClickButton(final String name) {

		super(name);
		type = Types.EVENT_CLICK;
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
	 * 按钮键值
	 */
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(final String key) {
		this.key = key;
	}

}