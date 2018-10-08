package benKeBiYeSheJi;

import commonTool.Output_xls_Data;

public class Write_Data {
	
	Output_xls_Data od = new Output_xls_Data();
	String path, sheetName, colName;//写入路径
	String path2, sheetName2, colName2;
	/**
	 * 写入各月月初库容（m³）、月入库径流量（m³）、各月月内水电站发电引水量（m³）、
	 * 各月月内弃水量（m³）、 各月月内出库流量（m³）、各月月末库容（m³）
	 */
	public Write_Data(){
		path = "F:\\本科毕业设计（2018））\\"
				+ "狮子滩水库信息\\狮子滩水库各月水量平衡.xls";
		sheetName = "月水量平衡";
		
		path2 = "F:\\本科毕业设计（2018））\\"
				+ "狮子滩水库信息\\狮子滩水库各月发电信息.xls";
		sheetName2 = "发电信息";
	}
	//各月月初库容（m³）
	public void write_YueChuKuRong(double[] yueChuKuRong){
		colName = "月初库容（m³）";
		od.writeColumnDatasDouble(path, sheetName, colName, yueChuKuRong);
	}
	//月入库径流量（m³）
	public void write_YueRuKuJingLiuLiang(double[] yueRuKuJingLiuLiang){
		colName = "月入库径流量（m³）";
		od.writeColumnDatasDouble(path, sheetName, colName, yueRuKuJingLiuLiang);
	}
	//各月月内水电站发电引水量（m³）
	public void write_YueFaDianYinShuiLiang(double[] yueFaDianYinShuiLiang){
		colName = "月发电引水量（m³）";
		od.writeColumnDatasDouble(path, sheetName, colName, yueFaDianYinShuiLiang);
	}
	//各月月内弃水量（m³）
	public void write_YueQiShuiLiang(double[] yueQiShuiLiang){
		colName = "月弃水量（m³）";
		od.writeColumnDatasDouble(path, sheetName, colName, yueQiShuiLiang);
	}
	//各月月内出库流量（m³）
	public void write_YueChuKuJingLiuLiang(double[] yueChuKuJingLiuLiang){
		colName = "月出库径流量（m³）";
		od.writeColumnDatasDouble(path, sheetName, colName, yueChuKuJingLiuLiang);
	}
	//各月月内出库平均流量（m³/s）
	public void write_YueChuKuPingJunLiuLiang(double[] yueChuKuPingJunLiuLiang){
		colName = "月出库平均流量（m³/s）";
		od.writeColumnDatasDouble(path, sheetName, colName, yueChuKuPingJunLiuLiang);
	}
	
	//各月月末库容（m³）
	public void write_YueMoKuRong(double[] yueMoKuRong){
		colName = "月末库容（m³）";
		od.writeColumnDatasDouble(path, sheetName, colName, yueMoKuRong);
	}
	//各月月初库水位（m）
	public void write_YueChuShuiWei(double[] yueChuShuiWei){
		colName = "月初水位（m）";
		od.writeColumnDatasDouble(path, sheetName, colName, yueChuShuiWei);
	}
	//各月月末库水位（m）
	public void write_YueMoShuiWei(double[] yueMoShuiWei){
		colName = "月末水位（m）";
		od.writeColumnDatasDouble(path, sheetName, colName, yueMoShuiWei);
	}
	
	
	//各月入库平均流量（m³/s）
	public void write_YueRuKuPingJunLiuLiang(double[] yueRuKuPingJunLiuLiang){
		colName2 = "月入库平均流量（m³/s）";
		od.writeColumnDatasDouble(path2, sheetName2, colName2, yueRuKuPingJunLiuLiang);
	}	
	//各月发电流量（m³/s）
	public void write_YueFaDianLiuLiang(double[] yueFaDianLiuLiang){
		colName2 = "月发电流量（m³/s）";
		od.writeColumnDatasDouble(path2, sheetName2, colName2, yueFaDianLiuLiang);
	}
	//各月发电水头差（m）
	public void write_YueFaDianShuiTouCha(double[] yueFaDianShuiTouCha){
		colName2 = "月发电水头差（m）";
		od.writeColumnDatasDouble(path2, sheetName2, colName2, yueFaDianShuiTouCha);
	}
	//月出力（kW）
	public void write_YueChuLi(double[] yueChuLi){
		colName2 = "月出力（kW）";
		od.writeColumnDatasDouble(path2, sheetName2, colName2, yueChuLi);
	}
	//月发电时长（h）
	public void write_YueFaDianShiChang(int[] yueFaDianShiChang){
		colName2 = "月发电时长（h）";
		od.writeColumnDatasInt(path2, sheetName2, colName2, yueFaDianShiChang);
	}
	//月发电量（kW·h）
	public void write_YueFaDianLiang(double[] yueFaDianLiang){
		colName2 = "月发电量（kW·h）";
		od.writeColumnDatasDouble(path2, sheetName2, colName2, yueFaDianLiang);
	}
	//总发电量（kW·h）
	public void write_FaDianZongLiang(double[] faDianZongLiang){
		colName2 = "总发电量（kW·h）";
		od.writeColumnDatasDouble(path2, sheetName2, colName2, faDianZongLiang);
	}
	
	
	
//	//测试
//	public static void main(String[] args) {
//		
//		Write_Data wd = new Write_Data();
//		
////		double[] arr = {1.1,2.2,3.3,4.1,5.9};
////		wd.write_YueChuKuRong(arr);
////		double[] arr2 = {1.1,2.2,3.3,4.1,5.9,1};
////		wd.write_YueRuKuJingLiuLiang(arr2);
////		double[] arr3 = {1.1,2.2,3.3,4.1,5.9,1,2};
////		wd.write_YueFaDianYinShuiLiang(arr3);
////		double[] arr4 = {1.1,2.2,3.3,4.1,5.9,1,2,3};
////		wd.write_YueQiShuiLiang(arr4);
////		double[] arr5 = {1.1,2.2,3.3,4.1,5.9,1,2,3,5};
////		wd.write_YueChuKuJingLiuLiang(arr5);
////		double[] arr6 = {1.1,2.2,3.3,4.1,5.9,1,2,3,5,6};
////		wd.write_YueMoKuRong(arr6);
////		double[] arr7 = {1.1,2.2,3.3,4.1,5.9,1,2,3,5,6,7,8};
////		wd.write_YueChuShuiWei(arr7);
////		double[] arr8 = {1.1,2.2,3.3,4.1,5.9,1,2,3,5,6,7};
////		wd.write_YueMoShuiWei(arr8);
//		
//		double[] arr9 = {1.2,2};
//		wd.write_YueFaDianLiuLiang(arr9);
//		double[] arr10 = {1.2,2,3};
//		wd.write_YueFaDianShuiTouCha(arr10);
//		double[] arr11 = {1.2,2,3,4};
//		wd.write_YueChuLi(arr11);
//		int[] arr12 = {1,2,3,4,5};
//		wd.write_YueFaDianShiChang(arr12);
//		double[] arr13 = {1,2,3,4,5,6};
//		wd.write_YueFaDianLiang(arr13);
//		double[] arr14 = {1,2,3,4,5,6,7};
//		wd.write_FaDianZongLiang(arr14);
//	}
	
}
