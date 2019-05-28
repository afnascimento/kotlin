package ${packageName}

import ${daggerPackageName}.*
import dagger.Binds
import dagger.Module

@Module
abstract class ${featureModuleClass} {

    @ActivityScoped
    @Binds
    internal abstract fun getView(activity: ${featureActivityClass}): ${featureViewClass}

    @ActivityScoped
    @Binds
    internal abstract fun getPresenter(presenter: ${featurePresenterImplClass}<${featureViewClass}, ${featureInteractorClass}>): ${featurePresenterClass}<${featureViewClass}, ${featureInteractorClass}>

    @ActivityScoped
    @Binds
    internal abstract fun getInteractor(interactor: ${featureInteractorImplClass}): ${featureInteractorClass}
}