package com.hafele.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hafele.dao.BookStyleDao;
import com.hafele.model.BookStyle;


/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年10月5日 下午10:00:11
* 
*/
public class MapUtil {
	static Map<String, Item> map = new HashMap<String, Item>();

	public static Map<String, Item> getMap() {
		List<?> list = BookStyleDao.selectBookStyle();
		for (int i = 0; i < list.size(); i++) {
			BookStyle bookStyle = (BookStyle) list.get(i);

			Item item = new Item();
			item.setId(bookStyle.getBookStyleNumber());
			item.setName(bookStyle.getBookStyle());
			map.put(item.getId(), item);

		}
		return map;
	}
}
