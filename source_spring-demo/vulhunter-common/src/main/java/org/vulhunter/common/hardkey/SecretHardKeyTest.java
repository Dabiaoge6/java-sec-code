package org.vulhunter.common.hardkey;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class SecretHardKeyTest {
    
    private final String secret = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCC4/E1+wAZ5TWIOmGVspzNW3fkpDXgFc4ohcIo3ZTMx4vOaSNcmvzBO3XgqbfyU+JZAiuhkFe0EOqZvFxcFzfxtIv5Nqo1uQZrQRNAnS4JQG6QEsGpq0PoWUXZAcSYQglYv0Bo4qBByhTaNyrsWfh7ex3VxrdnGDNyZLCHX9D3m9GwhNm7YEb+8TcfH7Y+CFGaOnyiOEtpat55ytwrNSByLeo6AXqXz53yBvwv5dLu17rZGyp5QL6JqpTbZz5DOIfG4f8bYooU/jb9YcDXV9guXZLTo8cZQ3Z3SC7msE44EaW77EhIbvcHSdIUqi+JZ7ZQdUIU2JoJUdplgZ3jBwLtAgMBAAECggEAW7chM/P3NC35/sT50A1h9jubnSi7X5On65keXcg2Fe/VNQNS9fbcGQJzLMN/01uYBUqSDBm6esBHwwXLqfPTDZi3ZB870X0e1FS7CtGYin8NegLKlPsfEFZNrSuh+BZ9uiP5HAWAydiyF94EQ60fglbYpw0hQiohEo0fbRxkrrsMctwc4uQ0cGX7HELJXucKUdEPf0T14CBfAhOxvfplkCVDHsajIHNL9eeEOoSNrkxs9rT5WihHyclVeDPwesK3xbSZwp8hkN6Lr0nK47UAqZ+IyGbhLh7h39SsSloUZ2UXk5jlb2jpLIJNhnPU5K20JarsvrvqosirubIlbYvxAQKBgQDMWQbfaJZmwz7Uj5yvlGcK7bafRKdFC1uOWRePz2JNGWd8Wf8/AlwFVCxVOqyas70E1vrTBla3MqjhrDUtWLHwNHh4DTSjkiO1lTXYbMT/3oM1Ce/YsnRfOk+Y7wpUnDKcJWCFLiuM7Yk/LZCUoqHGJ1i3ULlxt0xoNPT21JW9DQKBgQCj+Z9nq6dlDUp50/JxhQY/s2XPVnkUswzDvm/1D6lf+Gr8hPyi5g8VIwwLAwtZpATfn1rMGR6TaJ3IoIsF2iEp3wdFerO2uZ5UVMCVVY7F/XVRT+jK6F0qtkuMIMD/L7yRxf6x5b9+AWH4tAGU95ge8ly81vXdJcuzZmx6/vWlYQKBgQDDur5HfREaawciaFBHKRktsZVLpmGlU4SlqxZTa+uXN68Uv8spWiQ0AnTYSfQL1f9FxHjJaffjT4XNarogriT5k/iT3Oo/hnnnsny5yS1Ee6gnRFZUtFGc6OhW7xdxBY/AWljCGAAk41yjhAxJIiFcAFIMfCi0EEMCST8dsW+ffQKBgBoGqthnlGAjpeiETXzbIkzGCqjIDtvMN4ByH48D91w2L7T43qh0X3x5XZX527lUoTllL6IpcoLvHewUHzzEjHL65UqP2Sptd8Xm1dJeDBU8z3widM6P5wm6eW86W6elSgbRKEXQM36qdYRl/cJJMinnSfTnIYsCTrygyyMSiKbBAoGBALIqmZabjyQb87cxyCBw1CxAMYnnVFP78EG6QgnbWl43Y9AKcnfRcxkkR1cZ0PRP36p2947j0q4hnFlJtsMWSjgsghYBrqUSBQhSYcl9y4ChwCGUj0oPOZ7P2kYWSPwWBhiItlWFTE5y2VQ+C0LkV4R1mWFe5bFFtL3LNoVUqLqv";
    
    public void test() {
        KeyPairGenerator generator;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
    }

}
