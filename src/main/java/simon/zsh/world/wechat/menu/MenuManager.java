package simon.zsh.world.wechat.menu;

import java.util.Collections;
import java.util.List;

import simon.zsh.world.wechat.basis.ButtonBase;
import simon.zsh.world.wechat.utils.MenuUtil;

public abstract class MenuManager {

	/**
	 * 定义菜单结构
	 */
	private static Menu getMenu() {

		/**
		 * 菜单项一
		 */
		final ComplexButton btns1 = new ComplexButton("圣经查询");
		final List<ButtonBase> bible = btns1.getSub_button();

		final ClickButton btn11 = new ClickButton("精确查找");
		btn11.setKey("bible1");
		bible.add(btn11);

		final ClickButton btn12 = new ClickButton("模糊检索");
		btn12.setKey("bible2");
		bible.add(btn12);

		final ClickButton btn13 = new ClickButton("书卷与缩写");
		btn13.setKey("bible3");
		bible.add(btn13);

		final ClickButton btn14 = new ClickButton("随机经句");
		btn14.setKey("bible4");
		bible.add(btn14);

		final ClickButton btn15 = new ClickButton("每日箴言");
		btn15.setKey("bible5");
		bible.add(btn15);

		Collections.reverse(bible);

		/**
		 * 菜单项二
		 */
		final ComplexButton btns2 = new ComplexButton("荒漠甘泉");
		final List<ButtonBase> streams = btns2.getSub_button();

		final ClickButton btn21 = new ClickButton("今日");
		btn21.setKey("streams1");
		streams.add(btn21);

		final ClickButton btn22 = new ClickButton("昨日");
		btn22.setKey("streams2");
		streams.add(btn22);

		final ClickButton btn23 = new ClickButton("前日");
		btn23.setKey("streams3");
		streams.add(btn23);

		final ClickButton btn24 = new ClickButton("其它日期");
		btn24.setKey("streams4");
		streams.add(btn24);

		Collections.reverse(streams);

		/**
		 * 菜单项三
		 */
		final ComplexButton btns3 = new ComplexButton("关于平台");
		final List<ButtonBase> about = btns3.getSub_button();

		final ClickButton btn31 = new ClickButton("作者简介");
		btn31.setKey("about1");
		about.add(btn31);

		final ClickButton btn32 = new ClickButton("自由捐赠");
		btn32.setKey("about2");
		about.add(btn32);

		final ClickButton btn33 = new ClickButton("信经");
		btn33.setKey("about3");
		about.add(btn33);

		final ClickButton btn34 = new ClickButton("友情链接");
		btn34.setKey("about4");
		about.add(btn34);

		Collections.reverse(about);

		/**
		 * 菜单
		 */
		final Menu menu = new Menu();
		final List<ButtonBase> btns = menu.getButton();
		btns.add(btns1);
		btns.add(btns2);
		btns.add(btns3);

		return menu;
	}

	public static void main(String[] args) {

		// System.out.println(MenuUtil.getMenu());
		System.out.println(MenuUtil.deleteMenu() ? "成 功" : "失 败");
		System.out.println(MenuUtil.createMenu(getMenu()) ? "成 功" : "失 败");
	}
}