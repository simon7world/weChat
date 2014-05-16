package simon.zsh.world.wechat.send;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;

import simon.zsh.world.wechat.Types;
import simon.zsh.world.wechat.basis.SendMessageBase;
import simon.zsh.world.wechat.basis.ToXml;
import simon.zsh.world.wechat.tools.XmlTool;

public final class NewsSendMessage extends SendMessageBase {

	public NewsSendMessage() {

		setMsgType(Types.NEWS);
	}

	/**
	 * 图文消息个数，限制为8条以内
	 */
	private Integer articleCount;

	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(final Integer articleCount) {
		this.articleCount = articleCount;
	}

	/**
	 * 多条图文消息，默认第一个item为大图
	 */
	private List<Article> articles = new ArrayList<Article>();

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(final List<Article> articles) {
		this.articles = articles;
	}

	/**
	 * 图文消息
	 */
	public static final class Article implements ToXml {

		/**
		 * 图文消息名称
		 */
		private String title;

		public String getTitle() {
			return title;
		}

		public void setTitle(final String title) {
			this.title = title;
		}

		/**
		 * 图文消息描述
		 */
		private String description;

		public String getDescription() {
			return description;
		}

		public void setDescription(final String description) {
			this.description = description;
		}

		/**
		 * 图片链接，支持JPG、PNG格式，较好的效果为大图640像素 * 320像素，小图为 80像素 * 80像素
		 */
		private String picUrl;

		public String getPicUrl() {
			return picUrl;
		}

		public void setPicUrl(final String picUrl) {
			this.picUrl = picUrl;
		}

		/**
		 * 点击图文消息跳转链接
		 */
		private String url;

		public String getUrl() {
			return url;
		}

		public void setUrl(final String url) {
			this.url = url;
		}

		@Override
		public final Document toXml() {

			return new XmlTool().make(this, "item");
		}

	}

}