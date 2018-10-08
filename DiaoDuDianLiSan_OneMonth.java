package benKeBiYeSheJi;

import commonMath.MathFunction;

public class DiaoDuDianLiSan_OneMonth {

	public double[][] get_diaoDuLiSanDian_ZuHe(double step_temp, double[] diaoDuTu_temp){
		//新数组，对每个调度点按指定步长，向下搜索3步，向上搜索3步。
		double[][] diaoDuDian_LS = new double[2][7];
		for(int j = 0; j < 7; j ++){
			if(j < 4){//向下搜索2步
				diaoDuDian_LS[0][j] = diaoDuTu_temp[0] - j * step_temp;
				diaoDuDian_LS[1][j] = diaoDuTu_temp[1] - j * step_temp;
			}if(j > 3){//向上搜索2步
				diaoDuDian_LS[0][j] = diaoDuTu_temp[0] + (j-3) * step_temp;
				diaoDuDian_LS[1][j] = diaoDuTu_temp[1] + (j-3) * step_temp;
			}
		}
		MathFunction mf = new MathFunction();
		//组合：对同一月份两条调度线上的四个点进行组合
		double[][] diaoDuTu_LS_OneMonth = mf.combination_TwoArray(diaoDuDian_LS);
		return diaoDuTu_LS_OneMonth;
	}
	
	public double[][] get_diaoDuDianPanDuan(double[][] diaoDuTu_LS_OneMonth,
			double siShuiWei_Month, double zhengChangXuShuiWei_Month){
		//判断：
		for(int i = 0; i < diaoDuTu_LS_OneMonth.length; i ++){
			//对于每个月，调度线1的离散点必须小于等于调度线2；
			if(diaoDuTu_LS_OneMonth[i][0] > diaoDuTu_LS_OneMonth[i][1]){
//				//如果调度线1大于调度线2，则把调度线1降至调度线2位置
//				diaoDuTu_LS_OneMonth[i][0] = diaoDuTu_LS_OneMonth[i][1];
				//如果调度线1大于调度线2，则把调度线1升至调度线2位置
				diaoDuTu_LS_OneMonth[i][1] = diaoDuTu_LS_OneMonth[i][0];
			}
			//离散点必须处于死水位与正常蓄水位之间
			if(diaoDuTu_LS_OneMonth[i][0] < siShuiWei_Month){
				//如果调度线1低于死水位，则把调度线1升至死水位
				diaoDuTu_LS_OneMonth[i][0] = siShuiWei_Month;
			}else if (diaoDuTu_LS_OneMonth[i][0] > zhengChangXuShuiWei_Month){
				//如果调度线1高于正常蓄水位，则把调度线1降至正常蓄水位
				diaoDuTu_LS_OneMonth[i][0] = zhengChangXuShuiWei_Month;
			}
			if(diaoDuTu_LS_OneMonth[i][1] < siShuiWei_Month){
				//如果调度线2低于死水位，则把调度线1升至死水位
				diaoDuTu_LS_OneMonth[i][1] = siShuiWei_Month;
			}else if (diaoDuTu_LS_OneMonth[i][1] > zhengChangXuShuiWei_Month){
				//如果调度线2高于正常蓄水位，则把调度线2降至正常蓄水位
				diaoDuTu_LS_OneMonth[i][1] = zhengChangXuShuiWei_Month;
			}
		}
		return diaoDuTu_LS_OneMonth;
	}
	
	
//	//测试
//	public static void main(String[] args) {
//		DiaoDuDianLiSan_oneMonth dddlsm = new DiaoDuDianLiSan_oneMonth();
//		double step_temp = 10;
//		double[] diaoDuTu_temp = {335, 346};
//		double[][] arr = dddlsm.get_diaoDuLiSanDian(step_temp, diaoDuTu_temp);
//		for(int i = 0; i < arr.length; i ++){
//			for(int j = 0; j < arr[i].length; j ++){
//				System.out.println(arr[i][j]);
//			}
//			System.out.println("-------");
//		}
//		double siShuiWei_Month = 328.5;
//		double zhengChangXuShuiWei_Month = 346.3;
//		double[][] arr2 = dddlsm.get_diaoDuDianPanDuan(arr, 
//				siShuiWei_Month, zhengChangXuShuiWei_Month);
////		for(int i = 0; i < arr2.length; i ++){
////			for(int j = 0; j < arr2[i].length; j ++){
////				System.out.println(arr2[i][j]);
////			}
////			System.out.println("-------");
////		}
//		
//	}
}
