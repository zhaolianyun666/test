
/**
 * *һ����Ŀ�壺
1�����ݣ�
�ļ���Ա����Ϣ.txt���У���Ա��ְλ��Ա������������������Ϣ����ȡ��Щ��Ϣ��ͳ�Ƹ���ְλ�������Ұ��ղ�ְͬλ���й��࣬������ļ���Ա��ְλ��Ϣ.txt����չʾָ��ְλ��Ա����Ϣ��
2�� Ҫ��
1�� ����һ�����̣�����Practice5��
2�� ͨ��main�����������еķ�����changeEmployeeInfo������������Ϊ���������ļ�·��
3�����룺Ա����Ϣ.txt���ݸ�ʽʾ����
����  ����       5��
����  ��ƹ���ʦ 3��
����  ��������ʦ 2��
����  ���� 	 7��
����  ��������ʦ 6��
4�������Ա��ְλ��Ϣ.txt���ݸ�ʽʾ����
����  	   2��
����	     5��
����	     7��
��������ʦ 2��
����	     2��
����	     6��
��ƹ���ʦ 1��
����	     3��
 */
import java.io.*;
import java.util.*;

public class Practice5 {

	public static void changeEmployeeInfo(String source, String target) throws IOException {

		/**
		 * �ȶ�ȡ�ļ�����Ա��list(ÿһ�ж���һ������,ÿ���������3������)
		 */
		File file = new File(source);
		InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		List<EmployeeVo> employeeVoList = new ArrayList<EmployeeVo>();
		while ((line = br.readLine()) != null) { // ѭ����ȡ��
			String[] array = line.split("\t"); // ��tab�ָ�
			EmployeeVo employeeVo = new EmployeeVo();
			employeeVo.setName(array[0]);
			employeeVo.setJob(array[1]);
			employeeVo.setWorkingYears(array[2]);
			employeeVoList.add(employeeVo);
		}

		/**
		 * ����employeeVoList����Ŀ���ʽ���ݽṹ ��map�ṹ��ʾ;key��ְλ,value�����ְλ��Ա������
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
		 * ����Ŀ���ļ�
		 */
		File fout = new File(target);
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		for (String key : map.keySet()) {
			List<EmployeeVo> empList = map.get(key);
			bw.write(key + "\t" + empList.size() + "��");
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
		changeEmployeeInfo("D://Jstudy//Ա����Ϣ.txt", "D://Jstudy//Ա��ְλ��Ϣ.txt");
	}
}