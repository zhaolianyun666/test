
/**
 * *一、题目五：
1、内容：
文件“员工信息.txt”中，有员工职位、员工姓名、工作年限信息，读取这些信息，统计各个职位人数并且按照不同职位进行归类，在输出文件“员工职位信息.txt”中展示指定职位的员工信息。
2、 要求：
1） 构建一个工程，类名Practice5。
2） 通过main函数调用类中的方法（changeEmployeeInfo），方法参数为输入和输出文件路径
3）输入：员工信息.txt内容格式示例。
张三  经理       5年
李四  设计工程师 3年
王五  开发工程师 2年
陈六  经理 	 7年
赵七  开发工程师 6年
4）输出：员工职位信息.txt内容格式示例。
经理  	   2人
张三	     5年
陈六	     7年
开发工程师 2人
王五	     2年
赵七	     6年
设计工程师 1人
李四	     3年
 */
import java.io.*;
import java.util.*;

public class Practice5 {

	public static void changeEmployeeInfo(String source, String target) throws IOException {

		/**
		 * 先读取文件生成员工list(每一行都是一个对象,每个对象包含3个属性)
		 */
		File file = new File(source);
		InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		List<EmployeeVo> employeeVoList = new ArrayList<EmployeeVo>();
		while ((line = br.readLine()) != null) { // 循环读取行
			String[] array = line.split("\t"); // 按tab分割
			EmployeeVo employeeVo = new EmployeeVo();
			employeeVo.setName(array[0]);
			employeeVo.setJob(array[1]);
			employeeVo.setWorkingYears(array[2]);
			employeeVoList.add(employeeVo);
		}

		/**
		 * 遍历employeeVoList生成目标格式数据结构 用map结构表示;key是职位,value是这个职位的员工集合
		 */
		Map<String, List<EmployeeVo>> map = new LinkedHashMap<String, List<EmployeeVo>>();
		if (employeeVoList != null && employeeVoList.size() > 0) {
			for (EmployeeVo vo : employeeVoList) {
				String job = vo.getJob();
				List<EmployeeVo> tmpList = map.get(job);
				if (tmpList == null) {
					tmpList = new ArrayList<EmployeeVo>();
					tmpList.add(vo);
					map.put(job, tmpList);
				} else {
					tmpList.add(vo);
				}
			}
		}

		/**
		 * 生成目标文件
		 */
		File fout = new File(target);
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		for (String key : map.keySet()) {
			List<EmployeeVo> empList = map.get(key);
			bw.write(key + "\t" + empList.size() + "人");
			bw.newLine();
			bw.newLine();

			for (EmployeeVo vo : empList) {
				bw.write(vo.getName() + "\t" + vo.getWorkingYears());
				bw.newLine();
			}

			bw.newLine();
			bw.newLine();

		}
		bw.close();

	}

	public static void main(String[] args) throws IOException {
		changeEmployeeInfo("D://Jstudy//员工信息.txt", "D://Jstudy//员工职位信息.txt");
	}
}