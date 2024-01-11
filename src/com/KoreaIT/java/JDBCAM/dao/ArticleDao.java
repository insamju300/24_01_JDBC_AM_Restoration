package com.KoreaIT.java.JDBCAM.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.JDBCAM.container.Container;
import com.KoreaIT.java.JDBCAM.dto.Article;
import com.KoreaIT.java.JDBCAM.util.DBUtil;
import com.KoreaIT.java.JDBCAM.util.SecSql;

public class ArticleDao {

	public int doWrite(String title, String body) {

		SecSql sql = new SecSql();

		sql.append("INSERT INTO article");
		sql.append("SET regDate = NOW(),");
		sql.append("updateDate = NOW(),");
		sql.append("title = ?,", title);
		sql.append("`body`= ?,", body);
		sql.append("writerId= ?;", Container.session.loginedMemberId);

		return DBUtil.insert(Container.conn, sql);
	}

	public Map<String, Object> getArticleById(int id) {

		SecSql sql = new SecSql();

		sql.append("SELECT article.*, `member`.`name` AS writerName");
		sql.append("FROM article ");
		sql.append("INNER JOIN `member` ");
		sql.append("ON article.writerId = `member`.id ");
		sql.append("WHERE article.id = ?;", id);

		return DBUtil.selectRow(Container.conn, sql);
	}

	public void doDelete(int id) {
		SecSql sql = new SecSql();

		sql.append("DELETE FROM article");
		sql.append("WHERE id = ?;", id);

		DBUtil.delete(Container.conn, sql);
	}

	public void doUpdate(int id, String title, String body) {
		SecSql sql = new SecSql();

		sql.append("UPDATE article");
		sql.append("SET updateDate = NOW()");
		if (title.length() > 0) {
			sql.append(",title = ?", title);
		}
		if (body.length() > 0) {
			sql.append(",`body`= ?", body);
		}
		sql.append("WHERE id = ?;", id);

		DBUtil.update(Container.conn, sql);

	}

	public List<Article> getArticles() {
		SecSql sql = new SecSql();

		sql.append("SELECT article.*, `member`.`name` AS writerName");
		sql.append("FROM article ");
		sql.append("INNER JOIN `member` ");
		sql.append("ON article.writerId = `member`.id ");
		sql.append("ORDER BY article.id DESC;");

		List<Map<String, Object>> articleListMap = DBUtil.selectRows(Container.conn, sql);

		List<Article> articles = new ArrayList<>();

		for (Map<String, Object> articleMap : articleListMap) {
			articles.add(new Article(articleMap));
		}
		return articles;
	}

	public List<Article> searchArticles(Map<String, Object> map) {
		SecSql sql = new SecSql();

		sql.append("SELECT article.*, `member`.`name` AS writerName");
		sql.append("FROM article ");
		sql.append("INNER JOIN `member` ");
		sql.append("ON article.writerId = `member`.id ");
		if(map.containsKey("writerId")) {
			sql.append("WHERE article.writerId = ? ", (int)map.get("writerId"));	
		}
		sql.append("ORDER BY article.id DESC;");

		List<Map<String, Object>> articleListMap = DBUtil.selectRows(Container.conn, sql);

		List<Article> articles = new ArrayList<>();

		for (Map<String, Object> articleMap : articleListMap) {
			articles.add(new Article(articleMap));
		}
		return articles;
	}

}