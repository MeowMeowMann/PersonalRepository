package com.miniassignment.userValidationAssignment.sorting;

import com.miniassignment.userValidationAssignment.entity.UserEntity;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Sorting implementation for NameSort
public class NameSort implements SortStrategy{

    @Override
    public List<UserEntity> sort(List<UserEntity> users,String oe) {

//      Whether to sort by even or odd.
        if(oe.equalsIgnoreCase("even")){
                return users.stream()
                        .sorted(Comparator.comparingInt(user -> user.getName().length() % 2))
                        .collect(Collectors.toList());
        }
        else {
            return users.stream()
                    .sorted(Comparator.comparingInt(user -> (user.getName().length()) % 2 == 0 ? 1 : 0))
                    .collect(Collectors.toList());

        }
    }
}
