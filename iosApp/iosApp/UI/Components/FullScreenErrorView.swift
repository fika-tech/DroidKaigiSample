import SwiftUI
import shared

struct FullScreenErrorView: View {
    let error: KotlinThrowable
    let onClickRetry: () -> Void
    
    var body: some View {
        VStack(alignment: .center, spacing: 16) {
            Image("ErrorImage")
                .resizable()
                .aspectRatio(1, contentMode: .fit)
                .frame(maxWidth: 200)
            Text(error.message ?? "An error has occured")
                .bold()
                .font(.system(size: 18))
            Button("Retry", action: onClickRetry)
                .buttonStyle(.borderedProminent)
        }
    }
}

struct FullScreenErrorView_Previews: PreviewProvider {
    static var previews: some View {
        FullScreenErrorView(
            error: KotlinThrowable(message: "An error has occured"),
            onClickRetry: {}
        )
    }
}
