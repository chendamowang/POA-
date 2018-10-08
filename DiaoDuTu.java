package benKeBiYeSheJi;

import commonTool.Input_xls_Data;

public class DiaoDuTu {


	Input_xls_Data id = new Input_xls_Data();
	String path, sheetName, colName;

	double[] siShuiWei;	//死水位（m）
	double[] zhengChangXuShuiWei;//正常蓄水位或汛限水位（m）
	
	//将0.8倍、正常保证出力区间的上限水位放入同一个二维数组中
	//月份（1-12）、出力（0.8倍（12800kW、正常（16000kW））
	double[][] diaoDuShuiWei_CS;

	/**
	 * 随着类的加载，已将初始水库调度图中各月各水位读入
	 */
	public DiaoDuTu(){
		path = "F:\\研究生阶段\\本科毕业设计（2018））\\狮子滩水库信息\\初始调度图 - 副本.xls";
		sheetName = "初始调度图";
		colName = "死水位（m）";
		siShuiWei = id.getColumnDatasDouble(path, sheetName, colName);
	}
	/**
	 * 返回各月死水位：328.5m
	 * @return
	 */
	public double[] read_SiShuiWei(){		
		return siShuiWei;
	}
	/**
	 * 返回各月正常蓄水位 /汛限水位（346.3m/341.3m)
	 * @return
	 */
	public double[] read_ZhengChangXuShuiWei() {
		colName = "正常蓄水位/汛限水位（m）";
		zhengChangXuShuiWei = id.getColumnDatasDouble(path, sheetName, colName);	
		return zhengChangXuShuiWei;
	}
	
	
	//初始调度水位:一维表示月份、二维表示：索引0（0.8倍）、索引1（正常）
	public double[][] getDiaoDuShuiWei_CS() {
		colName = "0.8倍保证出力时水位（m）";//12800kW
		double[] shuiWei_1 = id.getColumnDatasDouble(path, sheetName, colName);
		colName = "正常保证出力时水位（m）";//16000kW
		double[] shuiWei_2 = id.getColumnDatasDouble(path, sheetName, colName);	
		//将正常保证出力所处水位区间下限、上限放入同一二维数组中
		diaoDuShuiWei_CS = new double[12][2];
		for(int i = 0; i < shuiWei_1.length; i ++){
			diaoDuShuiWei_CS[i][0] = shuiWei_1[i];
			diaoDuShuiWei_CS[i][1] = shuiWei_2[i];
		}
		return diaoDuShuiWei_CS;
	}
	
	
	/**
	 * 输入所在月份、初水位、调度水位，按照调度规则确定该月发电量（以水定电）
	 * @param yueFen int类型，月份（1-12）
	 * @param chuShiShuiWei 相应月月初水位（m）
	 * @param diaoDuTuShuiWei 调度图上的调度水位，按从低至高（1-12月）放入同一二维数组中
	 * @return  该月水电站的出力（kW）
	 */
	public double get_shuiDianZhanYueChuLi(int yueFen,double chuShiShuiWei, double[][] diaoDuTuShuiWei){
//		double baoZhengChuLi_1 = 12800;	//0.8倍保证出力（12800kW）
//		double baoZhengChuLi_2 = 16000;	//正常保证出力（16000kW）
//		double baoZhengChuLi_3 = 28800;	//1.8倍保证出力（28800kW）
		//调整水电站出力
		double baoZhengChuLi_1 = 9600;	//0.8倍保证出力（9600kW）
		double baoZhengChuLi_2 = 12000;	//正常保证出力（12000kW）
		double baoZhengChuLi_3 = 21600;	//1.8倍保证出力（21600kW）
		
		//某月在某一初始水位下相应的水电站出力
		double yueChuLi = 0.0;//若该月月初水位≤死水位，则该月水电站停止发电，即月出力为0.
		//由调度图中的调度规则选择该月水电站的出力
		if(chuShiShuiWei < siShuiWei[yueFen-1]){
			yueChuLi = 0.0;//该月停止发电
		}else if(chuShiShuiWei < diaoDuTuShuiWei[yueFen-1][0]){
			yueChuLi = baoZhengChuLi_1;//0.8倍保证出力区
		}else if(chuShiShuiWei < diaoDuTuShuiWei[yueFen-1][1]){
			yueChuLi = baoZhengChuLi_2;//正常保证出力区
		}else {
			yueChuLi = baoZhengChuLi_3;//1.8倍保证出力区
		}
		return yueChuLi;		
	}
	
	
//	//测试
//	public static void main(String[] args) {
//		DiaoDuTu ddt = new DiaoDuTu();
//		double[] arr2 = ddt.read_SiShuiWei();
//		double[] arr3 = ddt.read_ZhengChangXuShuiWei();
////		for(double d : arr3){
////			System.out.println(d);
////		}
//		double[][] arr = ddt.getDiaoDuShuiWei_CS();
////		for(int i = 0; i < arr.length; i ++){
////			for(int j = 0; j < arr[i].length; j ++){
////				System.out.println(arr[i][j]);
////			}
////			System.out.println("-----------");
////		}
//		int yueFen = 1;
//		double chuShiShuiWei = 346.5;
//		double yueChuLi = ddt.get_shuiDianZhanYueChuLi(yueFen, chuShiShuiWei, arr);
//		System.out.println(yueChuLi);
//	}
	
}
