package com.touhidapps.databindingexample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.touhidapps.databindingexample.R;
import com.touhidapps.databindingexample.databinding.ActivityExpressionLanguageBinding;
import com.touhidapps.databindingexample.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressionLanguageActivity extends AppCompatActivity {

    private ActivityExpressionLanguageBinding binding;

    private List<Person> personList = new ArrayList<>();
    private Map<String, Integer> students = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_expression_language);

        personList.add(new Person("Touhidul", "Islam"));
        students.put("my_id", 234);

        binding.setPersons(personList);
        binding.setStudents(students);

        binding.setMyEvent(new MyClickEvent());

    }

    public class MyClickEvent {
        public void switchImage(View v) {
            if (binding.getShowMyImage()) {
                binding.setShowMyImage(false);
            } else {
                binding.setShowMyImage(true);
            }

        }
    }


}
