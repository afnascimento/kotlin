package ${packageName}

import android.os.Bundle
import javax.inject.Inject
import ${basePackageName}.*

class ${featureActivityClass} : ${superClass}(), ${featureViewClass} {
   
   @Inject lateinit var mPresenter : ${featurePresenterClass}<${featureViewClass}, ${featureInteractorClass}>
   
   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}