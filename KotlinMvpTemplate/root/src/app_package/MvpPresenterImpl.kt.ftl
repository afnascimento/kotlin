package ${packageName}

import ${basePackageName}.*

import javax.inject.Inject

class ${featurePresenterImplClass}<V : ${featureViewClass}, I : ${featureInteractorClass}>
@Inject constructor(mView: V, mInteractor: I) : BasePresenterImpl<V, I>(mView, mInteractor), ${featurePresenterClass}<V, I> {
}