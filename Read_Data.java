package benKeBiYeSheJi;

import commonTool.Input_xls_Data;

public class Read_Data {

	Input_xls_Data id = new Input_xls_Data();
	String path, sheetName, colName;//读取路径
	int[] yueFen;//总的时段长度
	int[] yueTianShu;//各月天数
	int[] yueFaDianShiChang;//水电站机组月运行时数（h）
	double[] yueRuKuJingLiuLiang;//月入库径流量（m³）

	public Read_Data() {
		//狮子滩入库径流量
		path = "F:\\本科毕业设计（2018））\\狮子滩水库信息\\狮子滩月入库径流量.xls";
		sheetName = "Sheet1";
		//参与调度的总月份数
		colName = "月份";
		yueFen = id.getColumnDatasInt(path, sheetName, colName);
		//参与调度的各月份天数
		colName = "月天数（d）";
		yueTianShu = id.getColumnDatasInt(path, sheetName, colName);
		//参与调度的各月机组运行时段数
		colName = "水电站机组月运行时数（h）";
		yueFaDianShiChang = id.getColumnDatasInt(path, sheetName, colName);
		//狮子滩各月入库径流量
		colName = "月入库径流量（m³）";
		yueRuKuJingLiuLiang = id.getColumnDatasDouble(path, sheetName, colName);
	}
	//读取数据
	public int[] read_YueFen() {
		return yueFen;
	}
	public int[] read_YueTianShu() {
		return yueTianShu;
	}
	public int[] read_YueFaDianShiChang() {
		return yueFaDianShiChang;
	}
	public double[] read_YueRuKuJingLiuLiang() {
		return yueRuKuJingLiuLiang;
	}
	/**
	 * 由各月入库径流、天数，计算出各月入库月平均流量
	 * @return
	 */
	public double[] get_YueRuKuPingJunLiuLiang() {
		double[] yueRuKuJingLiuLiang = read_YueRuKuJingLiuLiang();
		int[] yueTianShu = read_YueTianShu();
		double[] yueRuKuPingJunLiuLiang = new double[yueRuKuJingLiuLiang.length];
		for(int i = 0; i < yueRuKuJingLiuLiang.length; i ++){
			yueRuKuPingJunLiuLiang[i] = yueRuKuJingLiuLiang[i] /
					(yueTianShu[i] * 24 * 3600);
		}
		return yueRuKuPingJunLiuLiang;
	}
	
	
//	//测试
//	public static void main(String[] args) {
//		Read_Data rd = new Read_Data();
//		int[] yueTianShu = rd.read_YueTianShu();
//		for(int i : yueTianShu){
////			System.out.println(i);
//		}
//		double[] arr = rd.get_YueRuKuPingJunLiuLiang();
//		for(double d : arr){
//			System.out.println(d);
//		}
//	}
	
}
