import fetchIntercept from "fetch-intercept";

//export const baseUrl = "http://reverseproxy";
export const baseUrl = "http://localhost:8089";

export function registerInterceptor() {
    fetchIntercept.register({
        request: function (url, config) {
            // Modify the url or config here
            return [url, config];
        },

        requestError: function (error) {
            // Called when an error occured during another 'request' interceptor call
            return Promise.reject(error);
        },

        response: function (response) {
            if (response.contentType = 'application/html') {
                console.log("this is a html")
            }

            console.log(response)
            // Modify the reponse object
            return response;
        },

        responseError: function (error) {
            // Handle an fetch error
            return Promise.reject(error);
        }
    });
}

export const endpoints = {
    product: {
        base: `${baseUrl}/api/v1/products`,
        products: `${baseUrl}/api/v1/products`,
        updateProduct: function ({ productId }) { return `${this.base}/api/v1/products/${productId}` },
        price: function ({ productId }) { return `${this.base}/api/v1/products/${productId}/price` },
        updateRating: function ({ productId }) { return `${this.base}/api/v1/products/${productId}/rating` }
    },

    vendor: {
        base: `${baseUrl}/api/v1/vendor`,
        vendor: `${baseUrl}/api/v1/vendor`,
        inventory: function ({ vendorId }) { return `${this.base}/${vendorId}/inventory` }
    },

    notification: {
        base: `${baseUrl}/api/v1/notification`,
        productMarking: function () { return `${this.base}/productmarking/` },
        deleteMarking: function ({ id }) { return `${this.base}/productmarking/${id}/` },
        perUser: function () { return `${this.base}/user/` },
    },

    user: {
        base: `${baseUrl}/api/v1/users`,
        current: `${baseUrl}/api/v1/user/me`,
        cart: function ({ userId }) { return `${this.base}/${userId}/cart/` },
        users: function () { return this.base + "/" },
        saveProduct: function ({ userId, productId }) { return `${this.base}/${userId}/cart/product/${productId}/` },
        removeProduct: function ({ userId, productId }) { return `${this.base}/${userId}/cart/product/${productId}/` },
    },

    shipment: {
        base: `${baseUrl}/api/v1/shipments`,
        status: function ({ uuid, status }) { return `${baseUrl}/api/v1/shipment/${uuid}/status/${status}/` }
    },

    transaction: {
        base: `${baseUrl}/api/v1`,
        forSeller: function ({ sellerId }) { return `${this.base}/sellers/${sellerId}/transactions/` },
        forBuyer: function ({ buyerId }) { return `${this.base}/buyers/${buyerId}/transactions/` },
        forUser: function ({ userId }) { return `${this.base}/users/${userId}/transactions/` },
        profits: function ({ sellerId }) { return `${this.base}/sellers/${sellerId}/profits/` },
    },
};

export const POST = {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
};

export const DELETE = {
    method: 'DELETE',
    headers: { 'Content-Type': 'application/json' },
};

export const api = {
    async getProducts() {
        let response = await fetch(endpoints.product.products);
        if (response.ok) {
            return await response.json();
        } else {
            console.error("HTTP-Error getting products: " + response.status);
            return [];
        }
    },

    async getVendorInventory({ vendorId }) {
        let response = await fetch(endpoints.vendor.inventory({ vendorId }));
        if (response.ok) {
            return await response.json();
        } else {
            console.error("HTTP-Error getting products: " + response.status);
            return [];
        }
    },

    async getProductMarkingsByUser(userId) {
        let response = await fetch(endpoints.notification.perUser + userId);
        if (response.ok) {
            return await response.json();
        } else {
            console.error("HTTP-Error getting product notfications: " + response.status);
            return [];
        }
    },

    async addProductMarking({ user, product, email: emailAddress, price }) {
        let response = await fetch(endpoints.notification.productMarking(), { ...POST, body: JSON.stringify({ user: user, product: product, emailAddress: emailAddress, price: price }) });
        if (response.ok) {
            return await response.json();
        } else {
            console.error("HTTP-Error adding product notification: " + response.status);
            return null;
        }
    },

    async deleteProductMarking({ uuid }) {
        let response = await fetch(endpoints.notification.deleteMarking(uuid), DELETE);
        if (response.ok) {
            return await response.json();
        } else {
            console.error("HTTP-Error deleting product notification: " + response.status);
            return null;
        }
    },

    async getNotifications({ userId }) {
        let response = await fetch(endpoints.notification.perUser(userId));
        if (response.ok) {
            return await response.json();
        } else {
            console.error("HTTP-Error getting product notification: " + response.status);
            return [];
        }
    },

    async getCurrentUser() {
        let response = await fetch(endpoints.user.current);
        if (response.ok) {
            return await response.json();
        } else {
            console.error("HTTP-Error getting current user: " + response.status);
            return null;
        }
    },

}


