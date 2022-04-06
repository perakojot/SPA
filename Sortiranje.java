package main;

public class Sortiranje {

	public static void MergeSort(int[] aArray)
	{
		mergeSort(aArray, 0, aArray.length - 1);
	}


	private static void mergeSort(int[] aArray, int aLeft, int aRight)
	{
		if (aRight > aLeft)
		{
			int middle = (aRight + aLeft) / 2;
			mergeSort(aArray, aLeft, middle);
			mergeSort(aArray, middle + 1, aRight);

			merge(aArray, aLeft, middle + 1, aRight);
		}
	}
	private static void merge(int[] aArray, int aLeft, int aMiddle, int aRight)
	{
		int i, left_end, num_elements, tmp_pos;

		num_elements = aRight - aLeft + 1;
		int[] temp_array = new int[aArray.length];
		left_end = aMiddle - 1;
		tmp_pos = aLeft;

		while ((aLeft <= left_end) && (aMiddle <= aRight))
		{
			if (aArray[aLeft] <= aArray[aMiddle])
			{
				temp_array[tmp_pos] = aArray[aLeft];
				aLeft++;
			}
			else
			{
				temp_array[tmp_pos] = aArray[aMiddle];
				aMiddle++;
			}
			tmp_pos++;
		}
		while (aLeft <= left_end)
		{
			temp_array[tmp_pos] = aArray[aLeft];
			tmp_pos++;
			aLeft++;
		}

		while (aMiddle <= aRight)
		{
			temp_array[tmp_pos] = aArray[aMiddle];
			tmp_pos++;
			aMiddle++;
		}

		for (i = 0; i < num_elements; i++)
		{
			aArray[aRight] = temp_array[aRight];
			aRight--;
		}
	} 


}
