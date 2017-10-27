package com.hafele.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年9月22日 下午11:51:33
* Document实现类
*/
@SuppressWarnings("serial")
public class MyDocument extends PlainDocument {
	int maxLength =10; 
	public MyDocument(int newMaxLength){ 
	   super(); 
	   maxLength = newMaxLength; 
	} 
    public MyDocument(){ 
	   this(10); 
	} 
	
    //重载父类的insertString函数 
    public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
    	if (getLength() + str.length() > maxLength) {//这里假定你的限制长度为10 
    		return;
    	} else {
    		super.insertString(offset, str, a);

    	}
    }
}
