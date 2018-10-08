package benKeBiYeSheJi;

import commonTool.ArrayTool;

public class ZongFaDianLiang_POA {
	
	int[] yueFen;//总的时段长度
	int[] yueFaDianShiChang;//水电站机组月运行时数（h）
	double[] yueRuKuJingLiuLiang;//月入库径流量（m³）
	double[] yueRuKuPingJunLiuLiang;//月入库平均流量（m³/s）

	//存储各月月初-月末水库信息
	double[] yueChuKuRong;//各月月初库容（m³）
	double[] yueChuShuiWei;//各月月初水位（m）
	double[] yueChuLi;//各月月内水电站出力（kW）
	double[] yueFaDianLiang;//各月月内水电站发电量（kW·h）	
	double[] yueFaDianShuiTouCha;//各月发电水头差（m）
	double[] yueFaDianLiuLiang;//各月月内水电站发电引水平均流量
	double[] yueFaDianYinShuiLiang;//各月月内水电站发电引水量
	double[] yueQiShuiLiang;//各月月内弃水量
	double[] yueChuKuJingLiuLiang;//各月月内水电站出库径流量
	double[] yueChuKuPingJunLiuLiang;//各月月内水电站出库平均流量
	double[] yueMoKuRong;//各月月末库容（m³）
	double[] yueMoShuiWei;//各月月末水位（m）
	double zongFaDianLiang;//所有月份总的发电量（kW·h）
	
	//水电站尾水位
	double weiShuiWei = 272.6;
	
	public ZongFaDianLiang_POA(){
		Read_Data rd = new Read_Data();
		yueFen = rd.read_YueFen();
		yueFaDianShiChang = rd.read_YueFaDianShiChang();
		yueRuKuJingLiuLiang = rd.read_YueRuKuJingLiuLiang();
		yueRuKuPingJunLiuLiang = rd.get_YueRuKuPingJunLiuLiang();
		int length = yueFen.length;//入库径流序列长度
		
		//存储各月月初-月末水库信息
		yueChuKuRong = new double[length];//各月月初库容（m³）
		yueChuShuiWei = new double[length];//各月月初水位（m）
		yueChuLi = new double[length];//各月月内水电站出力（kW）
		yueFaDianLiang = new double[length];//各月月内水电站发电量（kW·h）	
		yueFaDianShuiTouCha = new double[length];//各月发电水头差（m）
		yueFaDianLiuLiang = new double[length];//各月月内水电站发电引水平均流量
		yueFaDianYinShuiLiang = new double[length];//各月月内水电站发电引水量
		yueQiShuiLiang = new double[length];//各月月内弃水量
		yueChuKuJingLiuLiang = new double[length];//各月月内水电站出库径流量
		yueChuKuPingJunLiuLiang = new double[length];//各月月内出库平均流量
		yueMoKuRong = new double[length];//各月月末库容（m³）
		yueMoShuiWei = new double[length];//各月月末水位（m）
	}
	

			
	public void set_ShiZiTanShuiKuDiaoDu(double[][] diaoDuTuShuiWei){
		//调度图调度规则
		DiaoDuTu ddt = new DiaoDuTu();
		//发电引水量
		FaDianYinShuiLiang fdysl = new FaDianYinShuiLiang();
		//水位库容曲线
		ShuiWeiKuRongQuXian swkrqx = new ShuiWeiKuRongQuXian();
		//各月水位-库容约束
		double zhengChangXuShuiWei = 346.3;
		double zhengChangKuRong = 871000000;
		double xunXianShuiWei = 336.6;
		double xunXianKuRong = 322000000;
		double siShuiWei = 328.5;
		double siKuRong = 141000000;
		double chuLi_zhengChang = 12000;//正常保证出力（12000kW）
		double chuLi_max = 21600;//1.8倍保证出力（21600kW）
		double chuLi_min = 9600;//0.8倍保证出力（9600kW）
		
		//计算各月初-月末水库信息
		for(int i = 0; i < yueFen.length; i ++){
			if(i == 0){
				yueChuKuRong[i] = siKuRong;//月初水库库容（m³）
				yueChuShuiWei[i] = siShuiWei;//月初水库水位（m）
			}else {
				//月初水库库容（m³）
				yueChuKuRong[i] = yueMoKuRong[i-1];	
				//月初水库水位（m）
				yueChuShuiWei[i] = yueMoShuiWei[i-1];
			}
			//月内发电水头差	（m）
			yueFaDianShuiTouCha[i] = yueChuShuiWei[i] - weiShuiWei; 			
			//月内水库出力（kW）,以水库月初水位确定水电站出力
			yueChuLi[i] = ddt.get_shuiDianZhanYueChuLi(yueFen[i], 
					yueChuShuiWei[i], diaoDuTuShuiWei);	
			//初始月出力下的月末库容
			double yueMoKuRong_temp = jiSuanYueMoKuRong(yueChuLi[i], yueFaDianShuiTouCha[i],
					yueFaDianShiChang[i], yueChuKuRong[i], yueRuKuJingLiuLiang[i]);
			//相应月水位
			double yueMoShuiWei_temp = swkrqx.get_DangQianKuRong(yueMoKuRong_temp);
			
			//月末水库库容（m³）两种可能：≥死库容，＜死库容			
			if(yueMoKuRong_temp >= siKuRong){				
				//利用每个月的水库正常库容、汛限库容，按照月末水库库容判断是否产生弃水
				if(4 < yueFen[i] && yueFen[i] < 10){//处于汛期（5初-9月底）
					if(yueMoKuRong_temp > xunXianKuRong){
						yueQiShuiLiang[i] = yueMoKuRong_temp - xunXianKuRong;
						yueMoKuRong_temp = xunXianKuRong;
						yueMoShuiWei_temp = xunXianShuiWei;
					}
				}else {//处于非汛期
					if(yueMoKuRong_temp > zhengChangKuRong){
						yueQiShuiLiang[i] = yueMoKuRong_temp - zhengChangKuRong;	
						yueMoKuRong_temp = zhengChangKuRong;
						yueMoShuiWei_temp = zhengChangXuShuiWei;
					}				
				}
			}else {//月末库容＜死库容，降低出力，或停止发电
				if(yueChuLi[i] == chuLi_max){//若原月出力为21600kW
					//将原月最大出力降低为月正常出力
					yueChuLi[i] = chuLi_zhengChang;
					yueMoKuRong_temp = jiSuanYueMoKuRong(yueChuLi[i], yueFaDianShuiTouCha[i],
							yueFaDianShiChang[i], yueChuKuRong[i], yueRuKuJingLiuLiang[i]);
					yueMoShuiWei_temp = swkrqx.get_DangQianKuRong(yueMoKuRong_temp);
					if(yueMoKuRong_temp >= siKuRong){
						if(4 < yueFen[i] && yueFen[i] < 10){//处于汛期（5初-9月底）
							if(yueMoKuRong_temp > xunXianKuRong){
								yueQiShuiLiang[i] = yueMoKuRong_temp - xunXianKuRong;
								yueMoKuRong_temp = xunXianKuRong;
								yueMoShuiWei_temp = xunXianShuiWei;
							}
						}else {//处于非汛期
							if(yueMoKuRong_temp > zhengChangKuRong){
								yueQiShuiLiang[i] = yueMoKuRong_temp - zhengChangKuRong;	
								yueMoKuRong_temp = zhengChangKuRong;
								yueMoShuiWei_temp = zhengChangXuShuiWei;
							}				
						}
					}else {//降低至正常出力后的月库容仍＜死库容，则按月最小出力发电
						yueChuLi[i] = chuLi_min;
						yueMoKuRong_temp = jiSuanYueMoKuRong(yueChuLi[i], yueFaDianShuiTouCha[i],
								yueFaDianShiChang[i], yueChuKuRong[i], yueRuKuJingLiuLiang[i]);						
						yueMoShuiWei_temp = swkrqx.get_DangQianKuRong(yueMoKuRong_temp);
						if(yueMoKuRong_temp >= siKuRong){
							if(4 < yueFen[i] && yueFen[i] < 10){//处于汛期（5初-9月底）
								if(yueMoKuRong_temp > xunXianKuRong){
									yueQiShuiLiang[i] = yueMoKuRong_temp - xunXianKuRong;
									yueMoKuRong_temp = xunXianKuRong;
									yueMoShuiWei_temp = xunXianShuiWei;
								}
							}else {//处于非汛期
								if(yueMoKuRong_temp > zhengChangKuRong){
									yueQiShuiLiang[i] = yueMoKuRong_temp - zhengChangKuRong;	
									yueMoKuRong_temp = zhengChangKuRong;
									yueMoShuiWei_temp = zhengChangXuShuiWei;
								}				
							}
						}else {//降低至最小出力后的月库容仍＜死库容，则停止发电
							yueChuLi[i] = 0;
							yueMoKuRong_temp = yueChuKuRong[i] + yueRuKuJingLiuLiang[i];
							yueMoShuiWei_temp = swkrqx.get_DangQianShuiWei(yueMoKuRong_temp);
						}
					}
				}else if(yueChuLi[i] == chuLi_zhengChang){//若原月出力为12000kW
					//将原月出力下降为最小出力
					yueChuLi[i] = chuLi_min;
					yueMoKuRong_temp = jiSuanYueMoKuRong(yueChuLi[i], yueFaDianShuiTouCha[i],
							yueFaDianShiChang[i], yueChuKuRong[i], yueRuKuJingLiuLiang[i]);		
					yueMoShuiWei_temp = swkrqx.get_DangQianKuRong(yueMoKuRong_temp);
					if(yueMoKuRong_temp >= siKuRong){
						if(4 < yueFen[i] && yueFen[i] < 10){//处于汛期（5初-9月底）
							if(yueMoKuRong_temp > xunXianKuRong){
								yueQiShuiLiang[i] = yueMoKuRong_temp - xunXianKuRong;
								yueMoKuRong_temp = xunXianKuRong;
								yueMoShuiWei_temp = xunXianShuiWei;
							}
						}else {//处于非汛期
							if(yueMoKuRong_temp > zhengChangKuRong){
								yueQiShuiLiang[i] = yueMoKuRong_temp - zhengChangKuRong;	
								yueMoKuRong_temp = zhengChangKuRong;
								yueMoShuiWei_temp = zhengChangXuShuiWei;
							}				
						}
					}else {//降低为最小出力后的月库容仍＜死库容，则停止发电
						yueChuLi[i] = 0;
						yueMoKuRong_temp = yueChuKuRong[i] + yueRuKuJingLiuLiang[i];
						yueMoShuiWei_temp = swkrqx.get_DangQianShuiWei(yueMoKuRong_temp);
					}
				}else if(yueChuLi[i] == chuLi_min){//若原出力为9600kW，则停止发电
					yueChuLi[i] = 0;
					yueMoKuRong_temp = yueChuKuRong[i] + yueRuKuJingLiuLiang[i];
					yueMoShuiWei_temp = swkrqx.get_DangQianShuiWei(yueMoKuRong_temp);
				}
			}
			
			//月内水库平均引水发电流量（m³/s）
			yueFaDianLiuLiang[i] = fdysl.get_SDNYSFDLiuLiang(
					yueFaDianShuiTouCha[i], yueChuLi[i]);
			//月内水库发电引水量（m³）
			yueFaDianYinShuiLiang[i] = fdysl.get_SDNYSFZongLiang(
					yueFaDianLiuLiang[i], yueFaDianShiChang[i]);
			//月末水库库容（m³）
			yueMoKuRong[i] = yueMoKuRong_temp;
			//月末水库水位（m）
			yueMoShuiWei[i] = yueMoShuiWei_temp;
			//月内水库发电量（m³）				
			yueFaDianLiang[i] = yueChuLi[i] * yueFaDianShiChang[i];	
			//月内水库出库水量（m³）
			yueChuKuJingLiuLiang[i] = yueFaDianYinShuiLiang[i] + yueQiShuiLiang[i];
			//月内水库出库平均流量（m³/s）
			yueChuKuPingJunLiuLiang[i] = yueChuKuJingLiuLiang[i] / 
					(yueFaDianShiChang[i] * 3600);			
		}
		//总发电量
		zongFaDianLiang = ArrayTool.getSumElement(yueFaDianLiang);	
	}
	//由电站出力确定月末库容
	public double jiSuanYueMoKuRong(double yueFaDianShuiTouCha, double yueChuLi, 
			int yueFaDianShiChang, double yueChuKuRong, double yueRuKuJingLiuLiang){
		FaDianYinShuiLiang fdysl = new FaDianYinShuiLiang();
		//月内水库平均引水发电流量（m³/s）
		double yueFaDianLiuLiang = fdysl.get_SDNYSFDLiuLiang(
				yueFaDianShuiTouCha, yueChuLi);
		//月内水库发电引水量（m³）
		double yueFaDianYinShuiLiang = fdysl.get_SDNYSFZongLiang(
				yueFaDianLiuLiang, yueFaDianShiChang);
		ShuiLiangPingHeng slph = new ShuiLiangPingHeng();
		//月末水库库容（m³）
		double yueMoKuRong = slph.get_MoKuRong(yueChuKuRong, 
				yueRuKuJingLiuLiang, yueFaDianYinShuiLiang);
		return yueMoKuRong;
	}
	
	
	public double[] get_YueChuKuRong() {
		return yueChuKuRong;
	}
	public double[] get_YueChuShuiWei() {
		return yueChuShuiWei;
	}
	public double[] get_YueChuLi() {
		return yueChuLi;
	}
	public double[] get_YueFaDianLiang() {
		return yueFaDianLiang;
	}
	public double[] get_YueFaDianShuiTouCha(){
		return yueFaDianShuiTouCha;
	}
	public double[] get_YueFaDianLiuLiang() {
		return yueFaDianLiuLiang;
	}
	public double[] get_YueFaDianYinShuiLiang() {
		return yueFaDianYinShuiLiang;
	}
	public double[] get_YueQiShuiLiang() {
		return yueQiShuiLiang;
	}
	public double[] get_YueChuKuJingLiuLiang() {
		return yueChuKuJingLiuLiang;
	}
	public double[] get_YueChuKuPingJunLiuLiang() {
		return yueChuKuPingJunLiuLiang;
	}
	public double[] get_YueMoKuRong() {
		return yueMoKuRong;
	}
	public double[] get_YueMoShuiWei() {
		return yueMoShuiWei;
	}
	public double get_ZongFaDianLiang() {
		return zongFaDianLiang;
	}
	
	public double[] get_YueRuKuJingLiuLiang() {
		return yueRuKuJingLiuLiang;
	}
	public int[] get_YueFaDianShiChang() {
		return yueFaDianShiChang;
	}
	public double[] get_YueRuKuPingJunLiuLiang(){
		return yueRuKuPingJunLiuLiang;
	}
	
	

	//测试
	public static void main(String[] args) {
		ZongFaDianLiang_POA zfdlpoa = new ZongFaDianLiang_POA();
		DiaoDuTu ddt = new DiaoDuTu();
		//初始调度图-水位
		double[][] shuiWei_ChuShi = ddt.getDiaoDuShuiWei_CS(); 		
		zfdlpoa.set_ShiZiTanShuiKuDiaoDu(shuiWei_ChuShi);
		//水量平衡
		double[] yueChuKuRong = zfdlpoa.get_YueChuKuRong();
		double[] yueRuKuJingLiuLiang = zfdlpoa.get_YueRuKuJingLiuLiang();
		double[] yueFaDianYinShuiLiang = zfdlpoa.get_YueFaDianYinShuiLiang();
		double[] yueQiShuiLiang = zfdlpoa.get_YueQiShuiLiang();
		double[] yueChuKuJingLiuLiang = zfdlpoa.get_YueChuKuJingLiuLiang();
		double[] yueChuKuPingJunLiuLiang = zfdlpoa.get_YueChuKuPingJunLiuLiang();
		double[] yueMoKuRong = zfdlpoa.get_YueMoKuRong();
		double[] yueChuShuiWei = zfdlpoa.get_YueChuShuiWei();
		double[] yueMoShuiWei = zfdlpoa.get_YueMoShuiWei();
		//发电信息	
		double[] yueRuKuPingJunLiuLiang = zfdlpoa.get_YueRuKuPingJunLiuLiang();
		double[] yueFaDianLiuLiang = zfdlpoa.get_YueFaDianLiuLiang();
		double[] yueFaDianShuiTouCha = zfdlpoa.get_YueFaDianShuiTouCha();
		double[] yueChuLi = zfdlpoa.get_YueChuLi();
		int[] yueFaDianShiChang = zfdlpoa.get_YueFaDianShiChang();
		double[] yueFaDianLiang = zfdlpoa.get_YueFaDianLiang();
		double zongFaDianLiang = zfdlpoa.get_ZongFaDianLiang();
		double[] faDianZongLiang = new double[1];
		faDianZongLiang[0] = zongFaDianLiang;
				
		Write_Data wd = new Write_Data();
		//写入水量平衡信息
		wd.write_YueChuKuRong(yueChuKuRong);
		wd.write_YueRuKuJingLiuLiang(yueRuKuJingLiuLiang);
		wd.write_YueFaDianYinShuiLiang(yueFaDianYinShuiLiang);
		wd.write_YueQiShuiLiang(yueQiShuiLiang);
		wd.write_YueChuKuJingLiuLiang(yueChuKuJingLiuLiang);
		wd.write_YueChuKuPingJunLiuLiang(yueChuKuPingJunLiuLiang);
		wd.write_YueMoKuRong(yueMoKuRong);
		wd.write_YueChuShuiWei(yueChuShuiWei);
		wd.write_YueMoShuiWei(yueMoShuiWei);
		//写入水库发电信息
		wd.write_YueRuKuPingJunLiuLiang(yueRuKuPingJunLiuLiang);
		wd.write_YueFaDianLiuLiang(yueFaDianLiuLiang);
		wd.write_YueFaDianShuiTouCha(yueFaDianShuiTouCha);
		wd.write_YueChuLi(yueChuLi);
		wd.write_YueFaDianShiChang(yueFaDianShiChang);
		wd.write_YueFaDianLiang(yueFaDianLiang);
		wd.write_FaDianZongLiang(faDianZongLiang);
		
		
		//目标函数、总发电量、破坏率输出
		BaoZhengLv_PoHuaiLv bzlphl = new BaoZhengLv_PoHuaiLv();
		double baoZhengChuLi = 12000;
		double poHuaiLv = bzlphl.get_PoHuaiLv(yueChuLi, baoZhengChuLi);
		MuBiaoHanShu mbhs = new MuBiaoHanShu();
		double chengFaXiShu = 100000000.0;
		double muBiaoHanShu = mbhs.get_MuBiaoHanShuZhi_PHL(zongFaDianLiang, poHuaiLv, chengFaXiShu);
		System.out.println(poHuaiLv);
		System.out.println(muBiaoHanShu);
		System.out.println(faDianZongLiang[0]);
	}	
}
