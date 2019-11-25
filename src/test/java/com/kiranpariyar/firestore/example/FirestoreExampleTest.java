package com.kiranpariyar.firestore.example;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.junit.Test;

public class FirestoreExampleTest {

    @Test
    public void testAddData() throws ExecutionException, InterruptedException {

        FirestoreOptions firestoreOptions =
                FirestoreOptions.getDefaultInstance().toBuilder()
                        .setProjectId("impact-dev-crm")
                        .build();
        Firestore firestore = firestoreOptions.getService();

        DocumentReference docRef = firestore.collection("test-database").document("file_service");
        CollectionReference collectionReference = docRef.collection("file");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", "12345678");
        jsonObject.addProperty("tenantName", "test");
        jsonObject.addProperty("entityType", "lead");
        jsonObject.addProperty("entityId", 123454);
        jsonObject.addProperty("fileName", "hellofile");
        jsonObject.addProperty("userId", 12345);
        jsonObject.addProperty("createdAt", "2019-10-31");
        jsonObject.addProperty("exists", true);

        Gson gson = new Gson();
        String jsonString = gson.toJson(jsonObject);

        Map data = gson.fromJson(jsonString, Map.class);
        ApiFuture<DocumentReference> result = collectionReference.add(data);
        ApiFuture<DocumentSnapshot> documentReference = result.get().get();
        System.out.println("Update time : " + documentReference);
    }

}