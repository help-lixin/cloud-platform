package help.lixin.starlink.plugin.k8s.action.api;

import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.Arrays;

/**
 * 连接k8s案例
 */
public class ConnectionK8STest {

    private static final String KUBE_CONFIG = "/Users/ken/config/config";

    @Test
    @Deprecated
    public void testConnectionForFile() throws Exception {
        String kubeconfigContents = IOUtils.toString(new FileInputStream(KUBE_CONFIG), "UTF-8");
        Config config = Config.fromKubeconfig(kubeconfigContents);
        KubernetesClient client = new KubernetesClientBuilder().withConfig(config).build();
        NamespaceList list = client.namespaces().list();
        Assert.assertEquals(7, list.getItems().size());
    }

    @Test
    @Deprecated
    public void testMockCode() {
        io.fabric8.kubernetes.api.model.Config kubeConfig = new io.fabric8.kubernetes.api.model.Config();
        // apiVersion
        kubeConfig.setApiVersion("v1");

        NamedCluster namedCluster = new NamedCluster();
        Cluster cluster = new Cluster();
        cluster.setCertificateAuthorityData("LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUM2VENDQWRHZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFWTVJNd0VRWURWUVFERXdwcmRXSmwKY201bGRHVnpNQ0FYRFRJek1UQXhPREExTVRNMU9Gb1lEekl4TWpNd09USTBNRFV4TXpVNFdqQVZNUk13RVFZRApWUVFERXdwcmRXSmxjbTVsZEdWek1JSUJJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBUThBTUlJQkNnS0NBUUVBCjhjaXhLM0dvUjVrQTdwVU1PRDZLN0ZXTlZBblRSamhuMXlxamRzamNxU215WmRaNjZPNTlkdTdvVm1sTnB1dHkKbExxNVE5azMrWllBeTFoYUMyem1SeGpYS3IzK2NuQXM2MklkRWdFRnV5cWpEQllsNStobldwY3BwaEF3TEV2Nwp3L1FXSnVGNzJEZk9MRzVxeEtwNkh4b1o1VjhRNDdhZGVpaTFnaW5hRzMrZWoxU3ZzNmhPYzlXL0l2YllBdEFCCjdYZG56OEN1cDkxMEpmbE1TZjhqeHNpbSt5SWhML2laeTdDMk5QNC9TeGpEQ3RZRFFGYUM1TkhjR0xGNHcwQnAKSHZpUHUzN1lDN3FZbFdxRDN2S015anhwSUp2ZUR0UVVrTlBENEU2eGtXZ2dFVDZYbFZ6WUZTN1BLbWdhUDNhMwp2UDlsaXBVWEhPM3liaWxMQU9uV3F3SURBUUFCbzBJd1FEQU9CZ05WSFE4QkFmOEVCQU1DQXFRd0R3WURWUjBUCkFRSC9CQVV3QXdFQi96QWRCZ05WSFE0RUZnUVVqS2RnYW84eFhENFJ3aG9NWlA4N3YzQS9vUHd3RFFZSktvWkkKaHZjTkFRRUxCUUFEZ2dFQkFHb1hwbUQ3b2dhYVl5aTQ4azNxVUdUeTBZWkxrdy84aUNjU0ZZY1BjRFNKdDRyOQo2cmhrWG4za1J2bGJqQWNmS2h6R1VHaEZvRHdOZVJwc2g2Zlh3ZXp5ZWszK2lMOUxaaGQza2VucjZVVm5rRys5CkQzL0pVMzNIS0daUVhDYlVWc1JCc2N4dU5rSHZOYWhPWndjMWNudFpTRkd2RXpFWk5HeHY2L3k0V3ZkS28zQkwKK0tPUk4waEgydVFpb0N4RFZTeDZ3WlBWT3Vlc1FNWlJRSzNqMnRwbnk1TVZSR1JYZ2k5bXF5VWt3TjlZR0xiKwptZ2M2b1BOdEdGWUJUd3hWU0U3NUZtTVo1TVQxaytCQ1AwRlZiZW4wRW1panYzcVhYbzFYcW1ld1YwYU1BU2hqCjBSV0JQN0RqbzlDVWFwOHdDZUJQVmhQY24yM3c1TkhMLzVEM2lQVT0KLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo=");
        cluster.setServer("https://apiserver.cluster.local:6443");
        namedCluster.setCluster(cluster);
        namedCluster.setName("kubernetes");

        // clusters
        kubeConfig.setClusters(Arrays.asList(namedCluster));

        // contexts
        NamedContext namedContext = new NamedContext();
        Context context = new Context();
        context.setCluster("kubernetes");
        context.setUser("kubernetes-admin");
        namedContext.setContext(context);
        namedContext.setName("kubernetes-admin@kubernetes");

        // contexts
        kubeConfig.setContexts(Arrays.asList(namedContext));

        // current-context
        kubeConfig.setCurrentContext("kubernetes-admin@kubernetes");

        // kind
        kubeConfig.setKind("Config");

        // users
        NamedAuthInfo namedAuthInfo = new NamedAuthInfo();
        AuthInfo authInfo = new AuthInfo();
        authInfo.setClientCertificateData("LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSURGVENDQWYyZ0F3SUJBZ0lJTUVybGJlckIweFl3RFFZSktvWklodmNOQVFFTEJRQXdGVEVUTUJFR0ExVUUKQXhNS2EzVmlaWEp1WlhSbGN6QWdGdzB5TXpFd01UZ3dOVEV6TlRoYUdBOHlNVEl6TURreU5EQTFNVFF3TVZvdwpOREVYTUJVR0ExVUVDaE1PYzNsemRHVnRPbTFoYzNSbGNuTXhHVEFYQmdOVkJBTVRFR3QxWW1WeWJtVjBaWE10CllXUnRhVzR3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRRFk2RHR4UGlkSXZVbWQKWmVjL2JSb2IyeGxsUWRDRTJmcmRMQm1ldVl0VDhqTGt5YjBNNjRBWkRTZ1crRHdMMXhZN0FlRGR6ZEtKTVk3bwo0L2EwVjU0L25yOFdoUjZqLzRCTjVjLzdxcUVOMHdlcVNYMkJXTWZEb0wxaEtZTHpDR1BJOHY5U3RwNk1pUHorClMyUzJvVXdiUTYrWFFGMGIvTXY1SkU5MzhMaHNCclQxZUk4bytUQVUvcTZMTEY4bStFU2VpeDBKMGo5djhuaXYKZ2daeXJXMUpJejlKQzl2ZlpwYVZ0ekVDamlFRDh5M1hjNlcreEFTTG9ZMWpNRXc5OUtSSTQyR3J5cCtnK1ZTRwpCN0xucW1kci9FSXVwMHhJR1ZGR0RsODBCdjlmNmxhTmc4d09STEVjRnA3U21qSS90VFpWemJGTmxZRithanNtCnA2UnBINDNyQWdNQkFBR2pTREJHTUE0R0ExVWREd0VCL3dRRUF3SUZvREFUQmdOVkhTVUVEREFLQmdnckJnRUYKQlFjREFqQWZCZ05WSFNNRUdEQVdnQlNNcDJCcWp6RmNQaEhDR2d4ay96dS9jRCtnL0RBTkJna3Foa2lHOXcwQgpBUXNGQUFPQ0FRRUFPVzl1QWRJUWlJVFRITlV2QlQwcmxVTkZyVGpRR0ErV2owQWVMd044Rm1EVEM3SUgxSU9pCkMrKytybUN3Zm91bFZvbnYwWHBneWRVellzVUprbkNlV2hYZDZYczdVUHk5bnJRRkNoa0JOZGZrWHlWSmhKbVUKK3RmTVFuemdKeVc1N1hNTUV3SnBPbjJXWTlSTTBpR0pFREF0amkvcktNRlBvWTREeEZ2ZGxHQ2lyeG9Ja1dEVwpNU3N2RnhobDNrS1JCQXlJRHp1aHZBYmdUN0pWL1J2cWhnTndaY0lrcVoxWnZpUlhZK0Z0b2MzSXB4VzI0RHhrClpkVDFjaHpESWk1VkdCR05ydEUrNFYrTVBmbWp0Y3NaYXdUM20rUjJhK3BNTmd1bkpRc1IwSjNwRWgvUlhxb00KbTU3R3VacHhBZW9wUE1UNGJneUdZYm1OMlVrZWpTYUhqZz09Ci0tLS0tRU5EIENFUlRJRklDQVRFLS0tLS0K");
        authInfo.setClientKeyData("LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFcEFJQkFBS0NBUUVBMk9nN2NUNG5TTDFKbldYblAyMGFHOXNaWlVIUWhObjYzU3dabnJtTFUvSXk1TW05CkRPdUFHUTBvRnZnOEM5Y1dPd0hnM2MzU2lUR082T1AydEZlZVA1Ni9Gb1Vlby8rQVRlWFArNnFoRGRNSHFrbDkKZ1ZqSHc2QzlZU21DOHdoanlQTC9VcmFlaklqOC9rdGt0cUZNRzBPdmwwQmRHL3pMK1NSUGQvQzRiQWEwOVhpUApLUGt3RlA2dWl5eGZKdmhFbm9zZENkSS9iL0o0cjRJR2NxMXRTU00vU1F2YjMyYVdsYmN4QW80aEEvTXQxM09sCnZzUUVpNkdOWXpCTVBmU2tTT05ocThxZm9QbFVoZ2V5NTZwbmEveENMcWRNU0JsUlJnNWZOQWIvWCtwV2pZUE0KRGtTeEhCYWUwcG95UDdVMlZjMnhUWldCZm1vN0pxZWthUitONndJREFRQUJBb0lCQVFDWWhHTVhzMzVuOVVGSAovWmxtemM1bis5MlZoM3VaanJTSXhET1htaHZRQVNPc0gvWmdtVTlQVGlMOFU3dlZEMzVBSzRmMExuOTFuSFZDCmFOalY0aTRSeWd2UFIvZE5sYUNnSlpIakFnMlE3T3djM3VQekNYd2RvRzdKWFNrSXZzTjdUdklkTk8rekhiZlgKRHloWUNuNjdSUks0cGJkU3NLUzBJTVlJTGlsZk91T3ZNTmY3bVdtQnk4akttLzFDTm1lKzNKb01HUkl0V04yNApOOTRINllyQjBTVWdPb2NkNGtjVkJjcHBUMjFrV05mczFmc1QvUDQyNzFPSWJBUFUrQ2YzOUpqL0cvMGZKbkFJCkJpYkhmQlM1Qmp6MThycEExbFl3ekRIT1RWWnFWVVVpUDJMeTE1NTh2WkRlNGlBOHB4dkY5RHU5S0pRU3FkUVkKSW5xU1FtalJBb0dCQVA2SVNDV0MwRDBwUXhpZEFqK3F0bC82bjhORnM1dnFXN2Y0ZVBNNkVRbDdkc0p4bTlhUApXSk9LSldCd2x4Z3FQK2daYyt1UW0wUWNYZTh2SGpEOEhWejRZV0ZPSnRSVnZ3VytCbHhjZitXeTFpcy9ERmwwCi9nWWFTNEdXTkJPdGVrUGZwT3lvaGxBcEJESk1sZlZtSXFLdTh4VEdtNU45U2kySDF3RFhpQXc5QW9HQkFOb28KYVZDWGxnOXNpczZHazJoMWVIMnhlOFhOVFUxSFgrcDVTOFZUWTgrUnA1clgvcnF6MnBCM0ZSRld5bWtPL3lVNQpJS0JXVUFiU0krSW9TOFRzRVNNMFRrODdEQVdLWWc0VTNoYzNEY1lEbmpUcFVZR1RqOGhxTGJhWDNMME9jbGV0CnlqanlwVUFNaHJBTktTUVhVV1pDU3FjWXlsamU0ZjhDbGRPUzJsMUhBb0dBTlhYZ1dXaVpYazg5VlgwY3pNeUoKVTYxclF2TnYzdzZoUndtOE5iSDdoQmRYZ1UybFlySm04MDR2dllySVlYMzJRRVNnVVZqbEU3QTFscGc1NmlYNQo4TjRHYmc2aEh6WUhVaWh3WTVQV0ZTT3owZytEQTRNTUc3SUlLK2FsZDRDWFRvdzVzcTlPenFWZXlVWEd0VzFIClZ3NFI1a28wd1F5dXVOTndPREFNMWkwQ2dZQVdNV1E5VVo1dlpVQmRzQzBReEZxRWx1aWJmNzRzYldWaGlnTmUKU0pSdnlrWjlweGwvenNvZVJpNzdlNVBOeWp2RWdxSkNEV1hJdTlud3FON0VZcXQvcEZPUEZkZXNQSjZyQmc1dAoyQ2pyaXZTdU4wb1dYM2VTRU5NanJ5RkNqbFN0ZkwyRXU1SjZDSXd2SHJlZnQwdU5scExYbXRzQzNYR3FLbzRwClh0Z2VBd0tCZ1FEUmppQUt1blM1dmE2eUQ1SURPandoYzdqN3B2Y1YzVFM0K2JkSGZNTHJFWnM4a2lQYUhVYzAKZnhNMGJ2c0YzR0VaeFZKS29DNnZGNzlvM1FHa0RBai85a0F5WUNiOXU4TlUwcFlVS3ZsS1E4UERxUm94cDZNcgpvVnFZT2c3ajBFQ1BkaTlmTSszL2NMMWE2Umo5M1RZZlo3a2UraEpYY215NFJKVWp4QmlOSVE9PQotLS0tLUVORCBSU0EgUFJJVkFURSBLRVktLS0tLQo=");
        namedAuthInfo.setUser(authInfo);
        namedAuthInfo.setName("kubernetes-admin");

        //
        Config config = Config.empty();
        config.setApiVersion(kubeConfig.getApiVersion());
        // 可以不配置context
//        config.setContexts(kubeConfig.getContexts());
        // clusters.cluster.server
        config.setMasterUrl(cluster.getServer());

        // clusters.cluster.certificate-authority-data
        config.setCaCertData(cluster.getCertificateAuthorityData());
        // users.user.client-certificate-data
        config.setClientCertData(authInfo.getClientCertificateData());
        // users.user.client-key-data
        config.setClientKeyData(authInfo.getClientKeyData());
        // 可以不配置常用配置
        Config.configFromSysPropsOrEnvVars(config);

        KubernetesClient client = new KubernetesClientBuilder().withConfig(config).build();
        NamespaceList list = client.namespaces().list();
        Assert.assertEquals(7, list.getItems().size());
    }

    @Test
    public void testMockConfig() {
        String apiVersion = "v1";
        String server = "https://lb.kubesphere.local:6443";
        // 集群认证
        String certificateAuthorityData = "LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUMvakNDQWVhZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFWTVJNd0VRWURWUVFERXdwcmRXSmwKY201bGRHVnpNQjRYRFRJME1ESXdPREUzTURjeU4xb1hEVE0wTURJd05URTNNRGN5TjFvd0ZURVRNQkVHQTFVRQpBeE1LYTNWaVpYSnVaWFJsY3pDQ0FTSXdEUVlKS29aSWh2Y05BUUVCQlFBRGdnRVBBRENDQVFvQ2dnRUJBTUVPCk9DYU1OVnAwNitYVmNBaHFpV1lEZHR4T21UU0hBaE9SZDVncHJ1THF3cXZ3a0RVVGo1SlRCRHVzN1ZmYWlkdlMKVStaVGJrTTlISTFDVjVGYVdLZGVZRE51YlFYUHRSWWc4M1lNeHZ3dzZjRkZoa1NDMndjS085UFlybitQcmtURApyRTNsQS9ZYno5RytkelZUWnlCcGhMSVJEUFdNQWtkQ09JcnlMRWxJY0VjZ2dDTkJ6eWN2b3Mwb0V4UVJHNFBSCjBwdWc5QlVlU1ZVNHFmZWRYRTlMaVZhcDZTMkxXOVNiNXZOSTUyRENKbmcvT2ltejJLLzE4ZjVWbTVvMnlPdDQKWlFGcnlrc0JvWWhpS0xxSnVmdVlRL3c5ejFDY2FUWm1zdXRJakVJNmNsRy85ZTlMRFRpZFJUTjBkYVFOVXIvYgp5d1VTcHdrMURtVzFMRU5NVno4Q0F3RUFBYU5aTUZjd0RnWURWUjBQQVFIL0JBUURBZ0trTUE4R0ExVWRFd0VCCi93UUZNQU1CQWY4d0hRWURWUjBPQkJZRUZERStNTkw2YnUvWmUyT0JhRmRzZ0dFVG5wNkJNQlVHQTFVZEVRUU8KTUF5Q0NtdDFZbVZ5Ym1WMFpYTXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBR0xWZ2JaWGlwOFB3Y1QyVDN3NAp4L1djRUgvRG1RN2gzTmVvZ1p3Vkd4ZjdYTDFSa0IwRjd0b3A1ZzBzclQwRVp3QmV6blFwdzJzeFhuWmY3QUcwClBqUEFweWc4UXRCMTFnUGVoMmZJZEFDMFVtdi9HZ1VNVGVKb216czFsSFJYVDhQZDN3SWJmRFFmT3ZtM1BucHkKNDREenhab3FrYXhpaDJBOGZpOFVvaFhpZDhIRCtrWnROZ3VWb0ZibngwaitIbXpWR0gzdG5QQWRUSTdlVkwwNQpJTnRHYmNnM0hzZXlHQVFLd2pveUJzOVpYYXhLNjhuVjA5TE5XUVBHbFBiSE9RZUNCK0J0RW04emF2c0h4NTR3CjBCRC92R3lEY2lPYWkxczI2ZUx2VE1aaVRuR2tIWWIvbXNXV1RUNzlpK0I4ZmNDUjlwOGgydGNzVE9DaHo3MFgKeHBFPQotLS0tLUVORCBDRVJUSUZJQ0FURS0tLS0tCg==";

        String clientCertificateData = "LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSURJVENDQWdtZ0F3SUJBZ0lJSWpvM0RIU3lnTmN3RFFZSktvWklodmNOQVFFTEJRQXdGVEVUTUJFR0ExVUUKQXhNS2EzVmlaWEp1WlhSbGN6QWVGdzB5TkRBeU1EZ3hOekEzTWpkYUZ3MHlOVEF5TURjeE56QTNNamxhTURReApGekFWQmdOVkJBb1REbk41YzNSbGJUcHRZWE4wWlhKek1Sa3dGd1lEVlFRREV4QnJkV0psY201bGRHVnpMV0ZrCmJXbHVNSUlCSWpBTkJna3Foa2lHOXcwQkFRRUZBQU9DQVE4QU1JSUJDZ0tDQVFFQTBaeU9RUHlBY0VaQkw1RDQKUDM5UzFEY1FqSjhobUVuZklTeHpBV1FIanZWcUhwQWRBbi91RnpHcGZoQ3dtaEUxYmtiaU1KSDFvRVNiZERFdwpmOVp3Uk43U3FFS1gzZFh2TXNaTFJOb3E5bHFnSTJJZVhzM0JDcVVwNkpobmRKaVNCMkJvSDlhZ1NUMzVWSXpGCktvc09vK2tJOXlEWkVxNWR6Sm80VXNQNmJNUEF2NlpEZlpVTGZQUCtRVGQ2RHJaNjZhd3N3cVNhOXhXeENRSmYKRDRYbjBqbHA1bEw3anZyQ21xNTJ0bFpTYU5STjNFdWVIVVhWTFRRZkNkZTJncTBnQnZYZjV2Y1R6b1V6dnJ1NQoyWEd3Z1h6STdnekFHM2dwUXJNdnVqTzAxUldQbTVNS2NlaUVjVVFpMDg4YzA4dW5YeGU0THJodUxXMlMwK2FBCnpKQmE0d0lEQVFBQm8xWXdWREFPQmdOVkhROEJBZjhFQkFNQ0JhQXdFd1lEVlIwbEJBd3dDZ1lJS3dZQkJRVUgKQXdJd0RBWURWUjBUQVFIL0JBSXdBREFmQmdOVkhTTUVHREFXZ0JReFBqRFMrbTd2Mlh0amdXaFhiSUJoRTU2ZQpnVEFOQmdrcWhraUc5dzBCQVFzRkFBT0NBUUVBSHAyOXNiMUFrUE5NcHFVTFN0aWFZZ0JQVTVCKzNBNVVmSVRtCnkvT2grOHVQT0QyNkt5UkhDWmV6azg4emdRZmQ0VExyWk5lQVJoQ3Y1eGRCRElucFhXR2hGQkRqUXZOaEF5V3YKU25UamcyMDZ1L3dUQ1ZtaE1SR2d1Y3RZbkVrTnNEMUI4YXJhaUlFZ2N6WjVIeU1MWDJJVmRIQ1VyNGdUU0NMYwpUSERzNUxRdVkyOW9qZExoMnNaZUkxTWtkNWprSkpQSkZybmVzeThEVDNyVC9IL1JNRzZyOXpCQUJTYTBIQW0zCmIvbDJDQXp5V1hqUklVbzdRVGJGQTVkK2pyZUprTkVNNUNaR25GSnVnaHRCMEM5RGdRdDVmOXVjWHF0SHh2NTIKZ29LN1FZSGNOU1A0clkxRVJ6alROZHNkOXVKQlhpSlBWK3MvMGZmd01QbU1rTVBDTHc9PQotLS0tLUVORCBDRVJUSUZJQ0FURS0tLS0tCg==";
        String clientKeyData = "LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFb2dJQkFBS0NBUUVBMFp5T1FQeUFjRVpCTDVENFAzOVMxRGNRako4aG1FbmZJU3h6QVdRSGp2VnFIcEFkCkFuL3VGekdwZmhDd21oRTFia2JpTUpIMW9FU2JkREV3Zjlad1JON1NxRUtYM2RYdk1zWkxSTm9xOWxxZ0kySWUKWHMzQkNxVXA2SmhuZEppU0IyQm9IOWFnU1QzNVZJekZLb3NPbytrSTl5RFpFcTVkekpvNFVzUDZiTVBBdjZaRApmWlVMZlBQK1FUZDZEclo2NmF3c3dxU2E5eFd4Q1FKZkQ0WG4wamxwNWxMN2p2ckNtcTUydGxaU2FOUk4zRXVlCkhVWFZMVFFmQ2RlMmdxMGdCdlhmNXZjVHpvVXp2cnU1MlhHd2dYekk3Z3pBRzNncFFyTXZ1ak8wMVJXUG01TUsKY2VpRWNVUWkwODhjMDh1blh4ZTRMcmh1TFcyUzArYUF6SkJhNHdJREFRQUJBb0lCQUJUWEwvMmVIVmNNWlNiSgpqeVVoRDZjeDRsOVk0d28xTGc0cVVUVHVNU0l4bkY5MWpLelc4MkV5K3RzaDVhMHIwTmdxREFzd04rKzdZb2xtCjZwK0xQcFphWVMrQmozL3E2V0Z1L2NUV3ZEYktRK25BczZ4UllNUWlMOHlFZXFUSkZwdk5KZzBrVmNOeWVmdkMKRTBocmt3VVJBbWN2eU9WbW1NVGpBSDZhSmFmeWtJSytFVXU3SVpqR2tMZWY4UHJoQ0ZJTHpaZUxEbmtyWDBnZApOazBlcmx6aFYxYUxtdnllMEwwUUdGeFlaSmVJTC9Md0FXdDFCZDM0MXVRWk44SU1QUlcxSDVpK2hyR3FFdmcyCnNsZEduMjlZampPOXczUVNYYUNRRytyZXUxUWpyYXBXcldLWFZzQTMyYWlnR2wvcVRZcnRzcjlVVHFqbXpicXgKS1dveXYyRUNnWUVBNmFwRmovZndaOG55cFplYjBEUDc2QTFVTEhCSFZzdkJUN1JibWhHVzZRL1Zjb2dXYmFqZAoySlhNOCthSHd4S2R3WE5OeE5GbTIyVFExZmJKRzg1aEtMTXhLNEFWenBMWkQ1SE91czNSRHZaYTdrTitaeVJWCkZEcDBRbjc2V3lYSWxEcGpLbmozdzFJN2hVc2xOdTZBbGVTMWt1ZWNTT2U1Z1VYZjhrS0loaFVDZ1lFQTVhV3kKNDlxbE4wU3RydHBNbDZ2UmpKeGcwT003am40N3NJNG1TQjgyREVxbkQ3c25sOHBpS2lSSFNoQU5Zank1VWF0Mwo4UXdnN1doRENST3VidU5KQTZwdDU5L05wZ2Z3ZWpSblVTaUJhSlBHcFRrWUMwS256dndUc096Yndqd2x4OEV4CmRRODZQNlZMelhKSFp6c1NSMzFtS214a1czRXRTVWpzSnEvbDB4Y0NnWUJ0YkFFN1ZCNU9YREcyL2ZKSFk3Q1oKa0hSVWd4ZkZPNUdCSTdLcHlZYmtDVGdwZERVY2tUR2lWVFF2RzB1RVBGbUgxOWtaUy81Z3l5dElwMUt5Sk1rMgo2QU1rbmN5WWVnZ1AxSFg4NEpqLzZybldUZUVKcCtmM214QUtlNi85c3duazhCcTBMd1hIZitERG9OY0NqRm5HCjE5MTN0K0psK2EzVExjZWJpVENwcVFLQmdEdGwwMUphQURlK1hNb0lEcDJBUkhBQkZvMlE2SnVmak9MS3NaNGYKN0lQYlgwQ1RZTmRDOG9jQjRTcUg0bDVSWklGNlAwaXFvVzJtZFFoSS9lb1NnWlVnSk5NL3NOcEwzV2hPVzViRApTazE1UTMwQ3VuUlhKajVFTnZlaEcrb3VVbnRMUGZhUUhMblBrRDZ3VzBXWklzNDQ0ZzhCQUNnaXF0cjBzSmdGCkVRVzdBb0dBQzBqNjFUOFlPZ2RDNm95UU1RcHJieUdSZHVDOXJ1bXRiUDk3M2xHaHdueWllSGdPMDJiVldkS2sKZE85aUVRQ0V6NklsZk90TDFGVnJpZ1J6eXo4VHo3MEhJUlRxWEh3c1BGeFFZV2J4VHpPSVpVS0Q3MVNXbEdPdwpXRk9YT3dwYjNadkVabHZlYmsybGZKUXFwYkVFNUpramNqWWV5U213S0RVQmQ3b1h3ZjA9Ci0tLS0tRU5EIFJTQSBQUklWQVRFIEtFWS0tLS0tCg==";

        Config config = Config.empty();
        config.setApiVersion(apiVersion);
        // clusters.cluster.server
        config.setMasterUrl(server);

        // clusters.cluster.certificate-authority-data
        config.setCaCertData(certificateAuthorityData);
        // users.user.client-certificate-data
        config.setClientCertData(clientCertificateData);
        // users.user.client-key-data
        config.setClientKeyData(clientKeyData);
        // 可以不配置常用配置
        Config.configFromSysPropsOrEnvVars(config);

        KubernetesClient client = new KubernetesClientBuilder().withConfig(config).build();
        NamespaceList list = client.namespaces().list();
        Assert.assertEquals(7, list.getItems().size());
    }
}
