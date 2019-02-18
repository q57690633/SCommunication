package com.huxin.communication.utils;


import com.huxin.communication.entity.FamousEntity;

import java.util.Comparator;

/**
 * 
 * @author xiong
 *
 */
public class PinyinComparator implements Comparator<FamousEntity> {

	public int compare(FamousEntity o1, FamousEntity o2) {
		if (o1.getSortLetters().equals("@")
				|| o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
