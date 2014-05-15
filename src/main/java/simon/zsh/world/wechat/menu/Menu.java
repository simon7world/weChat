package simon.zsh.world.wechat.menu;

import java.util.ArrayList;
import java.util.List;

import simon.zsh.world.wechat.basis.ButtonBase;

/**
 * 菜单
 */
public final class Menu {

	private List<ButtonBase> button = new ArrayList<>();

	public List<ButtonBase> getButton() {
		return button;
	}

	public void setButton(final List<ButtonBase> button) {
		this.button = button;
	}
}