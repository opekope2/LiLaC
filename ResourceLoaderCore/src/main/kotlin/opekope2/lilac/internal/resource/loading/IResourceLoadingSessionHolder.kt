package opekope2.lilac.internal.resource.loading

import opekope2.lilac.api.resource.loading.IResourceLoadingSession
import opekope2.lilac.api.resource.loading.IResourceLoadingSession.IProperties

interface IResourceLoadingSessionHolder {
    fun getResourceLoadingSessionProperties(session: IResourceLoadingSession): IProperties
}
