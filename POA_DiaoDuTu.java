package benKeBiYeSheJi;

import commonMath.MathFunction;

public class POA_DiaoDuTu {
	
	public static void main(String[] args) {
		DiaoDuTu ddt = new DiaoDuTu();//调度图
		ZongFaDianLiang_POA zfdlpoa = new ZongFaDianLiang_POA();//总发电量
		BaoZhengLv_PoHuaiLv bzlphl = new BaoZhengLv_PoHuaiLv();//保证率-破坏率
		MuBiaoHanShu mbhs = new MuBiaoHanShu();//目标函数
		MathFunction mf = new MathFunction();//对二维素组中各元素进行组合
		DiaoDuDianLiSan_OneMonth dddlsom = new DiaoDuDianLiSan_OneMonth();//对一个月的调度点进行离散和判断
		
		//将初始调度线赋值给：调整调度线和优化调度线
		double[][] diaoDuTu_CS = ddt.getDiaoDuShuiWei_CS();
		double[][] diaoDuTu_temp = new double[diaoDuTu_CS.length][2];
		double[][] diaoDuTu_YH = new double[diaoDuTu_CS.length][2];
		for(int i = 0; i < diaoDuTu_CS.length; i ++){
			for(int j = 0; j < diaoDuTu_CS[i].length; j ++){
				diaoDuTu_temp[i][j] = diaoDuTu_CS[i][j];
				diaoDuTu_YH[i][j] = diaoDuTu_CS[i][j];
			}
		}	
		//惩罚系数
		double chengFaXiShu = 100000000.0;
		//保证出力
//		double baoZhengChuLi = 16000;	
		double baoZhengChuLi = 12000;//调整
		//初始总发电量、初始月出力、初始破坏率、初始目标函数值
		zfdlpoa.set_ShiZiTanShuiKuDiaoDu(diaoDuTu_CS);
		double zongFaDianLiang_CS = zfdlpoa.get_ZongFaDianLiang();
		double[] yueChuLi_CS = zfdlpoa.get_YueChuLi();
		double poHuaiLv_CS = bzlphl.get_PoHuaiLv(yueChuLi_CS, baoZhengChuLi);
		double muBiaoHanShuZhi_CS = mbhs.get_MuBiaoHanShuZhi_PHL(
				zongFaDianLiang_CS, poHuaiLv_CS, chengFaXiShu);
		System.out.println(poHuaiLv_CS);
		System.out.println(muBiaoHanShuZhi_CS);
		System.out.println(zongFaDianLiang_CS);
		System.out.println("-----------");
		//将初始目标函数值赋值给最优目标函数值
		double muBiaoHanShuZhi_max = muBiaoHanShuZhi_CS;
		double poHuaiLv = poHuaiLv_CS;//相应破坏率
		double zongFaDianLiang = zongFaDianLiang_CS;//相应总发电量
		//水位约束，每个月的离散点应处于死水位与正常蓄水位之间
		double[] siShuiWei_Month = ddt.read_SiShuiWei();
		double[] zhengChangXuShuiWei_Month = ddt.read_ZhengChangXuShuiWei();
		//初始搜索步长，设置时需保证调整点能覆盖正常蓄水位和死水位
		//在初水位处于正常蓄水位、死水位之间时，调整点能覆盖。
		double step = 6;
		//步长变换次数
		int number = 10;
//		int number = 20;
//		int number = 100;	
//		int number = 200;
		//各变换步长下，全年优化后总发电量、相应破坏率、相应总发电量		
		double[] step_arr = new double[number];
		double[] muBiaoHanShuZhi_maxArr = new double[number];
		double[] poHuaiLv_arr = new double[number];
		double[] zongFaDianLiang_arr = new double[number];
		
		for(int num = 0; num < number; num ++){//逐次变化步长
			System.out.println("步长变化" + num + "次");
			step_arr[num] = step - (step/number)*num;
			//从1月至12月，逐月优化调度图，然后并返回各月份对应的最大函数值，及相应的调度线1、调度线2
			for(int monthNum = 0; monthNum < 12; monthNum ++){//逐月优化调度点
				//对一个月的调度点进行调整、组合，得出该月的所有备用调度点
				//向下搜索3步，向上搜索3步。
				double[][] diaoDuTu_LS_OneMonth= dddlsom.get_diaoDuLiSanDian_ZuHe(step_arr[num],
						diaoDuTu_temp[monthNum]);
				double[][] diaoDuTu_LS_OneMonthNew = dddlsom.get_diaoDuDianPanDuan(diaoDuTu_LS_OneMonth,
						siShuiWei_Month[monthNum], zhengChangXuShuiWei_Month[monthNum]);

				//由每个月的多个储备调度点中，选择最优调度点，并返回最大函数目标值、最大总发电量、最大保证率
				for(int i = 0; i < diaoDuTu_LS_OneMonthNew.length; i ++){	
					diaoDuTu_temp[monthNum][0] = diaoDuTu_LS_OneMonthNew[i][0];
					diaoDuTu_temp[monthNum][1] = diaoDuTu_LS_OneMonthNew[i][1];
					zfdlpoa.set_ShiZiTanShuiKuDiaoDu(diaoDuTu_temp);
					double[] yueChuLi_temp = zfdlpoa.get_YueChuLi();
					double poHuaiLv_temp = bzlphl.get_PoHuaiLv(yueChuLi_temp, baoZhengChuLi);
//					if(poHuaiLv_temp <= poHuaiLv_CS){//新调度图的破坏率应不高于原调度图的破坏率
					//新调度图的破话率应不高于0.15
					if(poHuaiLv_temp <= 0.15){
						//破坏率逐渐减小
						if(poHuaiLv_temp <= poHuaiLv){
							double zongFaDianLiang_temp = zfdlpoa.get_ZongFaDianLiang();
							double muBiaoHanShuZhi_temp = mbhs.get_MuBiaoHanShuZhi_PHL(zongFaDianLiang_temp, 
									poHuaiLv_temp, chengFaXiShu);						
							if(muBiaoHanShuZhi_temp >= muBiaoHanShuZhi_max){//新目标函数应不低于上一次的目标函数
								muBiaoHanShuZhi_max = muBiaoHanShuZhi_temp;
								poHuaiLv = poHuaiLv_temp;
								zongFaDianLiang = zongFaDianLiang_temp;
								//以全年目标函数值最大下的调度水位为最优水位
								diaoDuTu_YH[monthNum][0] = diaoDuTu_temp[monthNum][0];
								diaoDuTu_YH[monthNum][1] = diaoDuTu_temp[monthNum][1];
							}
						}
					}
				}
				//将该月的最优调度线存入整条调整调度线中
				diaoDuTu_temp[monthNum][0] = diaoDuTu_YH[monthNum][0];
				diaoDuTu_temp[monthNum][1] = diaoDuTu_YH[monthNum][1];	
			}
			poHuaiLv_arr[num] = poHuaiLv;
			muBiaoHanShuZhi_maxArr[num] = muBiaoHanShuZhi_max;
			zongFaDianLiang_arr[num] = zongFaDianLiang;
		}
		System.out.println("优化步长变化：");
		for(double d : step_arr){
			System.out.println(d);
		}
		System.out.println("相应破坏率变化：");
		for(double d : poHuaiLv_arr){
			System.out.println(d);
		}
		System.out.println("优化后目标函数值变化：");
		for(double d : muBiaoHanShuZhi_maxArr){
			System.out.println(d);
		}
		System.out.println("相应总发电量变化：");
		for(double d : zongFaDianLiang_arr){
			System.out.println(d);
		}
		System.out.println("最终优化调度线：");
		for(int i = 0; i < 2; i ++){
			System.out.println("调度线"+(i+1));
			for(int j = 0; j < diaoDuTu_temp.length; j ++){
				System.out.println(diaoDuTu_temp[j][i]);
			}
		}
	}
}
