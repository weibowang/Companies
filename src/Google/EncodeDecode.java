package Google;

/*
 * 经典的地里出现过的String压缩编码解码类似题, 后悔当时看到没有好好写过一遍.给一个String比如"abcdfffffffxyz", 写两个methods, encode和decode. 
 * encode就是比如"fffffff"变成"7xf",decode就是要变为原字符串.我说"ff"怎么办,他说变成"2xf"你不觉得更长了吗? 我才明白了,应该是encoded后的String要比原来的短,
 * 不然为啥要encode,的亏我问了这个问题...然后又问他,如果原String本来就是"5xt"这种结构, decode不就无法辨认了吗?他说很高兴你提出了这个问题,但是不用管它,
 * 一会再讨论,先写吧.. 写完以后他就问我如果原String本来就是"5xt"这种结构,我encode应该怎么处理? 我就傻了... 
 * 因为一直觉得encode后的字符串长度一定要比原来的短,所以根本想不出来他要的解法. 说了四五种方法他都不满意, 最后给我hint说,要是有个"1xt"这样的你怎么处理? 
 * 当时脑洞大开想出来了... 其实是要变成三个"1xt"这种结构, 比如原String就是"5xq", 就encode成为"1x51xx1xq"就好了. 但是这种方法违背了encode后要变短的rule,
 * 所以我是真没想出来......
 * 还讨论了好多种情况, 最后一种是"1aaaaa"这种情况怎么变, 我说"1x15xa". 他说这是6个字符,能不能只用5个? 实在想不出来,这时候第三个小哥进来了,
 * 韩国哥哥就过来告诉我说,其实看做1a和aaaa两部分encode就好...
 */
public class EncodeDecode {
	
	public static void main(String[] args) {
		String s = "abccdddfffffffxyzzz";
		String encode = encode(s);
		System.out.println(encode);
		String decode = decode(encode);
		System.out.println(decode);
	}
	
	public static String encode(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		String result = "";
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (i != 0 && s.charAt(i) != s.charAt(i - 1)) {  //new char
				if (count > 2) {
					result += (count + "x" + s.charAt(i - 1));
				} else {
					for (int j = 0; j < count; j++) {
						result += s.charAt(i - 1);
					}
				}
				count = 1;
			} else {
				count++;
			}
		}
		if (count > 2) {
			result += (count + "x" + s.charAt(s.length() - 1));
		} else {
			for (int j = 0; j < count; j++) {
				result += s.charAt(s.length() - 1);
			}
		}
		return result;
	}
	
	
	public static String decode(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		String result = "";
		int i = 0;
		String strNum = "";
		while (i < s.length()) {
			char cur = s.charAt(i);
			if (Character.isDigit(cur)) {
				strNum += cur;
				i++;
			} else if (i != 0 && !Character.isDigit(cur) && Character.isDigit(s.charAt(i - 1))) {
				int num = Integer.parseInt(strNum);
				for (int j = 0; j < num; j++) {
					result += s.charAt(i + 1);
				}
				strNum = "";
				i = i + 2;
			} else {
				result += cur;
				i++;
			}
		}
		return result;
	}
}
