package simon.zsh.world.wechat.menu;

import java.util.ArrayList;
import java.util.List;

import simon.zsh.world.wechat.basis.ButtonBase;
import simon.zsh.world.wechat.utils.menu.MenuUtil;

public class MenuManager {

	/**
	 * 定义菜单结构
	 */
	private static Menu getMenu() {
		ClickButton btn11 = new ClickButton();
		btn11.setName("公司介绍");
		btn11.setType("click");
		btn11.setKey("oschina");

		ClickButton btn12 = new ClickButton();
		btn12.setName("设计师");
		btn12.setType("click");
		btn12.setKey("iteye");

		ClickButton btn13 = new ClickButton();
		btn13.setName("优惠活动");
		btn13.setType("click");
		btn13.setKey("iteye");

		ViewButton btn21 = new ViewButton();
		btn21.setName("创睿装饰");
		btn21.setType("view");
		btn21.setUrl("http://cr-decoration.cc/");

		ClickButton btn22 = new ClickButton();
		btn22.setName("公司简介");
		btn22.setType("click");
		btn22.setKey("iteye");

		ClickButton btn23 = new ClickButton();
		btn23.setName("公司新闻");
		btn23.setType("click");
		btn23.setKey("iteye");

		ClickButton btn24 = new ClickButton();
		btn24.setName("装饰案例");
		btn24.setType("click");
		btn24.setKey("iteye");

		ClickButton btn25 = new ClickButton();
		btn25.setName("联系我们");
		btn25.setType("click");
		btn25.setKey("iteye");

		List<ButtonBase> complexBtnList = new ArrayList<>();
		complexBtnList.add(btn21);
		complexBtnList.add(btn22);
		complexBtnList.add(btn23);
		complexBtnList.add(btn24);
		complexBtnList.add(btn25);
		ComplexButton complexBtn1 = new ComplexButton();
		complexBtn1.setName("公司介绍");
		complexBtn1.setSub_button(complexBtnList);

		Menu menu = new Menu();
		List<ButtonBase> buttonList = new ArrayList<>();
		buttonList.add(btn12);
		buttonList.add(btn13);
		buttonList.add(complexBtn1);
		menu.setButton(buttonList);

		return menu;
	}

	public static void main(String[] args) {

		final boolean result = MenuUtil.createMenu(getMenu());

		if (result) {
			System.out.println("菜单创建成功！");
		} else {
			System.out.println("菜单创建失败！");
		}
	}

}