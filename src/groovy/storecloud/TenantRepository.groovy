package storecloud

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import grails.plugin.multitenant.core.Tenant
import grails.plugin.multitenant.core.CurrentTenant
import grails.plugin.multitenant.core.CurrentTenantAwareRepository

/**
 * Dummy implementation of TenantRepository
 * TODO: Make your changes
 * @author Kim A. Betti
 */
public class TenantRepository implements CurrentTenantAwareRepository {

    private static final Logger log = LoggerFactory.getLogger(this)

    CurrentTenant currentTenant // will be injected

    @Override
    Tenant getCurrentTenant() {
        Tenant tenantInstance = null
        Integer currentTenantId = currentTenant.get()
        if (currentTenantId != null) {
            tenantInstance = findByTenantId(currentTenantId)
        }
        
        tenantInstance
    } 

    @Override
    Tenant findByTenantId(Integer tenantId) {
        Shop.get(tenantId); // Assuming that you use Shop's primary key as tenant id
    }

}