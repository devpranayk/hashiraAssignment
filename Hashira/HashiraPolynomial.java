import org.json.JSONObject;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class HashiraPolynomial {
    public static void main(String[] args) throws Exception {
        String content = new String(Files.readAllBytes(Paths.get("input.json")));
        JSONObject obj = new JSONObject(content);

        // here im extracting the values of n and k from the JSON.
        int n = obj.getJSONObject("keys").getInt("n");
        int k = obj.getJSONObject("keys").getInt("k");

        //converting all the given roots into decimal form.
        List<BigInteger> roots = new ArrayList<>();
        for (String key : obj.keySet()) {
            if (key.equals("keys")) continue;
            JSONObject root = obj.getJSONObject(key);
            int base = root.getInt("base");
            String value = root.getString("value");
            roots.add(new BigInteger(value, base));
        }

        List<BigInteger> selectedRoots = roots.subList(0, k);

        //We begin constructing the polynomial coefficients.
        List<BigInteger> coeffs = new ArrayList<>();
        coeffs.add(BigInteger.ONE);

        // For every root r, we multiply the polynomial by (x - r).
        for (BigInteger r : selectedRoots) {
            List<BigInteger> newCoeffs = new ArrayList<>(Collections.nCopies(coeffs.size() + 1, BigInteger.ZERO));
            for (int i = 0; i < coeffs.size(); i++) {
                newCoeffs.set(i, newCoeffs.get(i).subtract(r.multiply(coeffs.get(i))));
                newCoeffs.set(i + 1, newCoeffs.get(i + 1).add(coeffs.get(i)));
            }
            coeffs = newCoeffs;
        }

        System.out.print("Polynomial: ");
        for (int i = coeffs.size() - 1; i >= 0; i--) {
            BigInteger c = coeffs.get(i);
            if (c.equals(BigInteger.ZERO)) continue;

            if (i == coeffs.size() - 1) {
                System.out.print(c + "x^" + i);
            } else {
                System.out.print((c.signum() >= 0 ? " + " : " - ") + c.abs() + (i > 0 ? "x^" + i : ""));
            }
        }

        //Printing the raw list of coefficients.
        System.out.println("\nCoefficients: " + coeffs);
    }
}
