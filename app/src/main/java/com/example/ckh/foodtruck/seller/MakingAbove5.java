package com.example.ckh.foodtruck.seller;

import java.util.ArrayList;

/**
 * Created by HOME on 2016-09-24.
 */

/**
* class name : MakingAbove5
* method : quickSort
* method of parameter : 정렬대상인 ArrayList, left 인덱스(0), right인덱스 (ArrayList~.size()-1)
* method using : 퀵정렬을 통해 정렬 대상인 ArrayList를 정렬한다.
* class using : 퀵정렬을 통해 각 구를 오름차순으로 정렬한다. 내림차순으로 하려 했으나 퀵정렬의 특성상 역순정렬은 시간복잡도가 O(n^2)가 되므로 정순으로..
                각 구를 정렬하는 기준은 남, 여 유동인구수의 합계로 한다.
* */
public class MakingAbove5 {
    ArrayList<MovingPeople> arrayTop5;
    MovingPeople tempPivot;
    int pivotp=0;

    // quickSort(arr, 0 ,arr.length-1)
     public void quickSort(ArrayList<MovingPeople> sorting, int left, int right){
         if(left>=right){
             return;
         }

         int low=left+1;
         int high=right;
         MovingPeople pivot=sorting.get(left);

         while(low<=high) {
             while (low <= right && (pivot.male + pivot.female) >= (sorting.get(low).female + sorting.get(low).male))
                 low++;

             while(left+1<=high&&(pivot.male+pivot.female)<=(sorting.get(high).female+sorting.get(high).male)){
                 high--;
             }

             if(low<=high){
                 MovingPeople temp=sorting.get(low);

                 sorting.add(low,sorting.get(high));
                 sorting.remove(low+1);

                 sorting.add(high,temp);
                 sorting.remove(high+1);

             }else{

                 sorting.add(left,sorting.get(high));
                 sorting.remove(left+1);
                 sorting.remove(high);
                 sorting.add(high,pivot);


             }

         }
        quickSort(sorting,left,high-1);
         quickSort(sorting,high+1,right);
         }


     }