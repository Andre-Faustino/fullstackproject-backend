package com.andrefaustino.fullstackproject.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	public static String encodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static List<Integer> decodeListInteger(String s) {
		String[] vet = s.split(",");
		List<Integer> list = new ArrayList<>();

		for (String x : vet) {
			list.add(Integer.parseInt(x));
		}

		return list;

	}
}
