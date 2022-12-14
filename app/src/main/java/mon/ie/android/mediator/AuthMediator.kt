package mon.ie.android.mediator

import mon.ie.auth.AuthApi
import mon.ie.auth.AuthFeature
import mon.ie.common.base.BaseMediator
import mon.ie.common.base.FeatureFacade
import mon.ie.common.extensions.nullable

class AuthMediator : BaseMediator() {

    private var featureHolder: FeatureFacade<AuthApi>? by nullable {
        AuthFeature()
    }

    override val feature
        get() = requireNotNull(featureHolder)

    override fun clearFeature() {
        featureHolder = null
    }
}