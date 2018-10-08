package benKeBiYeSheJi;

/**
 * 包含两个方法：计算时段T内平均发电引水流量、计算时段T内发电引水总量。
 *
 */
public class FaDianYinShuiLiang {

	double chuLiXiShu = 9.81;
	double jiZuZongXiaoLvZhi = 0.85;
	double liuLiang_min = 18.72;//引水发电最小流量（m³/）
	double liuLiang_max = 62.2;//引水发电最大流量（m³/s）
	
	/**
	 * 在给定水头差下，指点时段T内的保证出力，计算该时段内平均引水流量（m³/s）
	 * @param yueFaDianShuiTouCha 各月月内水头差（m）
	 * @param dianZhanChuLi 各月月内电站出力（kW）
	 * @return 返回时段T内平均发电引水流量（m³/s），如果电站出力为0则引水发电流量为0
	 */
	public double get_SDNYSFDLiuLiang(
			double yueFaDianShuiTouCha, double dianZhanChuLi){
		//时段内水电站平均发电引水流量（m³/s）
		double faDianLiuLiang = (dianZhanChuLi / (jiZuZongXiaoLvZhi * 
				chuLiXiShu * yueFaDianShuiTouCha));
		if(faDianLiuLiang < liuLiang_min){
			faDianLiuLiang = 0.0;
		}else if(faDianLiuLiang >= liuLiang_max){
			faDianLiuLiang = liuLiang_max;
		}		
		return faDianLiuLiang;
	}
	

	/**
	 * 在给发电引水流量、月发电时长下，计算该时段发电引水总量（m³）
	 * @param faDianLiuLiang 发电流量（m³/s)
	 * @param faDianShiChang 发电时长（h）
	 * @return  月发电引水量（m³）
	 */
	public double get_SDNYSFZongLiang(double faDianLiuLiang, int faDianShiChang){
		double yinShuiLiang = (faDianLiuLiang * (faDianShiChang * 3600));
		return yinShuiLiang;
	}
}
