package com.prokurelabs.app;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record PurchaseOrderDraft(String ownerId) {
}
