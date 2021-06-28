package com.andy.jennifer.systemTemplate.src


/**
 * Author:  andy.xwt
 * Date:    2021/6/28 14:23
 * Description:
 */

fun activityJava(
    activityClass: String,
    activityLayout: String,
    fragmentClass: String,
    fragmentPackage: String,
    packageName: String,
    superClassFqcn: String
) =

    """package ${packageName};

import ${superClassFqcn};
import android.os.Bundle;
import ${packageName}.${fragmentPackage}.${fragmentClass};

public class $activityClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.${activityLayout});
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, ${fragmentClass}.newInstance())
                .commitNow();
        }
    }
}
"""