package simon.zsh.world.wechat.menu;

import java.util.ArrayList;
import java.util.List;

import simon.zsh.world.wechat.basis.ButtonBase;

/**
 * 复合类型的按钮
 */
public final class ComplexButton extends ButtonBase {

	public ComplexButton(final String name) {

		super(name);
	}

	/**
	 * 二级按钮
	 */
	private List<ButtonBase> sub_button = new ArrayList<>();;

	public List<ButtonBase> getSub_button() {
		return sub_button;
	}

	public void setSub_button(final List<ButtonBase> sub_button) {
		this.sub_button = sub_button;
	}

}