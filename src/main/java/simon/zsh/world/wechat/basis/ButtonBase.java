package simon.zsh.world.wechat.basis;

/**
 * 按钮的基类
 */
public abstract class ButtonBase {

	public ButtonBase(final String name) {

		this.name = name;
	}

	/**
	 * 按钮名称
	 */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}
}