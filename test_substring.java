public class test_substring
{
	public static void main(String[] args)
	{
		String a, b;
		substring s = new substring();
		a = "qwer1234qwerqwer1234qqqqwerqwwweeqwe";
		b = "qwer";
		int i;
	    System.out.println("Rabin-Karp:");
		int rk[] = s.RK(a, b);
		System.out.println();
		System.out.println("Boyer-Moore:");
		int bm[] = s.BM(a, b);
		System.out.println();
		System.out.println("Knuth-Morris-Pratt:");
		int kmp[] = s.KMP(a, b);
	}
}