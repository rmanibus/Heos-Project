package fr.lavachequicode.heos.sdk.services;

import org.fourthline.cling.binding.annotations.*;
import org.fourthline.cling.model.types.ServiceId;
import org.fourthline.cling.model.types.UDAServiceId;
import org.fourthline.cling.model.types.UnsignedIntegerFourBytes;

@UpnpService(
        serviceId = @UpnpServiceId("ContentDirectory"),
        serviceType = @UpnpServiceType(value = "ContentDirectory", version = 1)
)
public interface ContentDirectory {

    ServiceId serviceId = new UDAServiceId("ContentDirectory");

    @UpnpAction(name = "GetSearchCapabilities", out = @UpnpOutputArgument(name = "SearchCaps"))
    String getSearchCapabilities();

    @UpnpAction(name = "GetSortCapabilities", out = @UpnpOutputArgument(name = "SortCaps"))
    String getSortCapabilities();

    @UpnpAction(name = "GetSystemUpdateID", out = @UpnpOutputArgument(name = "Id"))
    UnsignedIntegerFourBytes getSystemUpdateID();

    @UpnpAction(name = "Browse", out = {
            @UpnpOutputArgument(name = "Result",
                    stateVariable = "A_ARG_TYPE_Result",
                    getterName = "getResult"),
            @UpnpOutputArgument(name = "NumberReturned",
                    stateVariable = "A_ARG_TYPE_Count",
                    getterName = "getCount"),
            @UpnpOutputArgument(name = "TotalMatches",
                    stateVariable = "A_ARG_TYPE_Count",
                    getterName = "getTotalMatches"),
            @UpnpOutputArgument(name = "UpdateID",
                    stateVariable = "A_ARG_TYPE_UpdateID",
                    getterName = "getContainerUpdateID")
    })
    String browse(
            @UpnpInputArgument(name = "ObjectID", aliases = "ContainerID") String objectId,
            @UpnpInputArgument(name = "BrowseFlag") String browseFlag,
            @UpnpInputArgument(name = "Filter") String filter,
            @UpnpInputArgument(name = "StartingIndex", stateVariable = "A_ARG_TYPE_Index") UnsignedIntegerFourBytes firstResult,
            @UpnpInputArgument(name = "RequestedCount", stateVariable = "A_ARG_TYPE_Count") UnsignedIntegerFourBytes maxResults,
            @UpnpInputArgument(name = "SortCriteria") String orderBy);

    @UpnpAction(name = "Search", out = {
            @UpnpOutputArgument(name = "Result",
                    stateVariable = "A_ARG_TYPE_Result",
                    getterName = "getResult"),
            @UpnpOutputArgument(name = "NumberReturned",
                    stateVariable = "A_ARG_TYPE_Count",
                    getterName = "getCount"),
            @UpnpOutputArgument(name = "TotalMatches",
                    stateVariable = "A_ARG_TYPE_Count",
                    getterName = "getTotalMatches"),
            @UpnpOutputArgument(name = "UpdateID",
                    stateVariable = "A_ARG_TYPE_UpdateID",
                    getterName = "getContainerUpdateID")
    })
    String search(
            @UpnpInputArgument(name = "ContainerID", stateVariable = "A_ARG_TYPE_ObjectID") String containerId,
            @UpnpInputArgument(name = "SearchCriteria") String searchCriteria,
            @UpnpInputArgument(name = "Filter") String filter,
            @UpnpInputArgument(name = "StartingIndex", stateVariable = "A_ARG_TYPE_Index") UnsignedIntegerFourBytes firstResult,
            @UpnpInputArgument(name = "RequestedCount", stateVariable = "A_ARG_TYPE_Count") UnsignedIntegerFourBytes maxResults,
            @UpnpInputArgument(name = "SortCriteria") String orderBy);
}
