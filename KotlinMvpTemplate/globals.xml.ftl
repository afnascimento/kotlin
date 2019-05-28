<?xml version="1.0"?>
<globals>
   
	<global id="featureViewClass" value="${featureName}View" />
	<global id="featurePresenterClass" value="${featureName}Presenter" />
	<global id="featureInteractorClass" value="${featureName}Interactor" />
	
	<global id="featurePresenterImplClass" value="${featureName}PresenterImpl" />
    <global id="featureInteractorImplClass" value="${featureName}InteractorImpl" />
	
	<global id="featureActivityClass" value="${featureName}Activity" /> 
	<global id="featureModuleClass" value="${featureName}Module" />
	
	<global id="resOut" value="${resDir}" />
    <global id="srcOut" value="${srcDir}/${slashedPackageName(packageName)}" />
	
</globals>