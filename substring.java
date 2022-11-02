class substring
{
	public substring() {}
	public int[] RK(String T, String P)
	{
		int d = 256;
		int q = 101;
		int n = T.length();
		int m = P.length();
        int d_m = (int) (Math.pow(d, m-1)%q);
        int results[] = new int[n/m];
        int ind = 0;
		int h, tmp_h, i, j, s;
		h = 0;
		tmp_h = 0;
		for(i = 0; i < m; i++)
		{
			h = (d*h + P.charAt(i))%q;
			tmp_h = (d*tmp_h + T.charAt(i))%q;		
		}
		for(s = 0; s < n-m+1; s++)
		{
			if(h == tmp_h)
			{
				for(j = 0; j < m; j++)
				{
					if(T.charAt(j+s) != P.charAt(j))
					{
						break; //collision
					}
				}
				if(j == m)// P found
				{
					results[ind] = s;
					System.out.print(s + " ");
					ind++;
				}
			}
			if(s < n-m)
			{
                tmp_h = (d*(tmp_h - d_m *T.charAt(s)) + T.charAt(s+m))%q;
                tmp_h = (tmp_h + q)%q;
			}
		}
		return results;
	}
    public int[] prefix_function(String S)
    {
    	int s = S.length();
        int[] pi = new int[s];
        int k = 0;
        pi[0] = 0;
        for (int i = 1; i < s; i++)
        {
            while (k > 0 && S.charAt(k) != S.charAt(i))
            {
                k = pi[k-1];
            }
            if (S.charAt(k) == S.charAt(i))
            {
                k = k + 1;
            }
            pi[i] = k;
        }
        return pi;
    }
    private int[] getSuffixTable(String P)
    {
        int m = P.length();
        int[] pref = prefix_function(P);
        int[] pref_r = prefix_function(reverse(P));
        int[] table = new int[m+1];
        int i, ind, shift;
        for (i = 0; i < m+1; i++)
        {
            table[i] = m - pref[m-1];
        }
        for (i = 0; i < m; i++)
        {
            ind = m - pref_r[i];
            shift = i - pref_r[i] + 1;
            if (table[ind] > shift)
            {
                table[ind] = shift;
            }
        }
        return table;
    }
    private static String reverse(String str) 
    {
        return new StringBuilder(str).reverse().toString();
    }
    public int[] getStopTable(String P)
    {
        int m = P.length();
        int[] StopTable = new int[256];
        for (int i = 0; i < 256; i++)
            StopTable[i] = -1;
        for (int i = 0; i < m-1; i++)
        {
            for (int j = 0; j < m-1; j++) 
            {
                if(P.charAt(i) == P.charAt(j)) 
                {
                    StopTable[P.charAt(i)] = j;
                }
            }
        }
        return StopTable;
    }
	public int[] BM(String T, String P)
	{
		int n = T.length();
		int m = P.length();
        int results[] = new int[n/m];
        int ind = 0;
        int [] suff = getSuffixTable(P);
        int [] stop = getStopTable(P);
        int i, j, delta_stop, delta_suff;
        i = 0;
        while (i < n - m +1)
        {
            j = m-1;
            while (j >= 0 && P.charAt(j) == T.charAt(i+j))
            {
                j = j - 1;
            }
            if(j == -1)
            {
                results[ind] = i;
				System.out.print(i + " ");
                ind++;
                delta_stop = 1;
            }
            else
            {
                delta_stop = j - stop[T.charAt(i+j)];
            }
            delta_suff = suff[j+1];
            i += Math.max(delta_stop, delta_suff);
        }
        return results;
	}
    public int[] KMP(String T, String P)
    {
		int n = T.length();
		int m = P.length();
        int[] results = new int[n/m];
        int ind = 0;
        int[] pi = prefix_function(P);
        int k = 0;
        for (int i = 0; i < n; i++)
        {
            while (k > 0 && P.charAt(k) != T.charAt(i))
            {
                k = pi[k-1];
            }
            if(P.charAt(k) == T.charAt(i))
            {
                k = k+1;
            }
            if(k == m) 
            {
                results[ind] = i - m + 1;
				System.out.print(i - m + 1 + " ");
                k = pi[k-1];
                ind++;
            }
        }
        return results;
    }	
}