package com.gem.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import com.gem.entity.Car;

public class ParseCookie {
	public static List<Car> getCookietoCars(Cookie[] cookies, int uid) {
		List<Car> carList = new ArrayList<Car>();//空集合
		if (cookies == null) {//没有任何cookie,购物车里面没有数据
			return carList;//购物车为空
		}
		//有cookie,找有没有购物车的cookie
		for (int i = 0; i < cookies.length; i++) {
			//规定存在cookie的购物车的key为car[所有用户]
			if (cookies[i].getName().equals("car")) {
				//有购物车的cookie
				try {
					//所有用户的购物车信息
					String allCarInfors = URLDecoder.decode(cookies[i].getValue(), "utf-8");//"3-2-1;3-3-4;6-7-8"
					String[] cargoods = allCarInfors.split(";"); // ["3-2-1","3-3-4","6-7-8"]
					for (int j = 0; j < cargoods.length; j++) {
						String cargood = cargoods[j];//3-2-1
						String[] splitcargood = cargood.split("-");
						if (splitcargood[0].equals(uid + "")) {// 3-2-1 分割成 car对象
							Car car = new Car(null, uid, splitcargood[1], Integer.parseInt(splitcargood[2]));
							carList.add(car);//购物车的商品
						}
					}

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return carList;//空,没有购物车的空
	}

	//购物添加商品
	public static String addDrugs(List<Car> carList, int uid, String did, int num) {
		boolean flage = false;//默认该商品不存在购物车
		//先查询该商品时候再购物中
		for (int i = 0; i < carList.size(); i++) {
			Car car = carList.get(i);//获得car
			if (car.getDid().equals(did)) {
				flage = true;//该商品在购物车中存在
				car.setNum(num);//存在,数量添加
				break;
			}
		}

		if (!flage) {//不存在
			Car car = new Car(null, uid, did, num);
			carList.add(car);//追加到集合中,多个商品
		}
		//购物变成字符串的形式,为了存在cookie中
		String carString = "";
		for (int i = 0; i < carList.size() - 1; i++) {//最后一个没有;
			Car car = carList.get(i);
			carString += car.getUid() + "-" + car.getDid() + "-" + car.getNum() + ";";
		}
		//最后一个对象
		Car car = carList.get(carList.size() - 1);//最后一个商品
		carString += car.getUid() + "-" + car.getDid() + "-" + car.getNum();

		//编码一下,存到cookie中
		String string = "";
		try {
			string = URLEncoder.encode(carString, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return string;
	}

	public static String getCarToString(List<Car> carList) {
		if (carList != null && carList.size() > 0) {
			String carString = "";
			for (int i = 0; i < carList.size() - 1; i++) {
				Car car = carList.get(i);
				carString += car.getUid() + "-" + car.getDid() + "-" + car.getNum() + ";";
			}
			Car car = carList.get(carList.size() - 1);//最后一个商品
			carString += car.getUid() + "-" + car.getDid() + "-" + car.getNum();
			String string = "";
			try {
				string = URLEncoder.encode(carString, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return string;
		}
		return null;
	}

}
