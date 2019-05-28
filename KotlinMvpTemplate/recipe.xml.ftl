<?xml version="1.0"?>
<recipe>

	<instantiate from="src/app_package/MvpView.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${featureName}View.kt" />
	
	<instantiate from="src/app_package/MvpInteractor.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${featureName}Interactor.kt" />
				   
    <instantiate from="src/app_package/MvpPresenter.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${featureName}Presenter.kt" />
				   
	<instantiate from="src/app_package/MvpInteractorImpl.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${featureName}InteractorImpl.kt" />
				   
    <instantiate from="src/app_package/MvpPresenterImpl.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${featureName}PresenterImpl.kt" />
				   
    <instantiate from="src/app_package/MvpActivity.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${featureName}Activity.kt" /> 
				 
	<instantiate from="src/app_package/MvpModule.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${featureName}Module.kt" />
				   
	<open file="${srcOut}/${featureName}Activity.kt"/>
</recipe>