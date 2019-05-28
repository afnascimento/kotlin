package ${packageName}

import ${basePackageName}.*

import ${dataPackageName}.*

import javax.inject.Inject

class ${featureInteractorImplClass}
@Inject constructor(mDataManager: DataManager) : BaseInteractorImpl(mDataManager), ${featureInteractorClass} {

}