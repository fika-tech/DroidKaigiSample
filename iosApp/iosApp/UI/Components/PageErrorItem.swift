import SwiftUI
import shared

struct PageErrorItem: View {
    let error: KotlinThrowable
    let onClickRetry: () -> Void
    
    var body: some View {
        HStack(alignment: .center, spacing: 8) {
            Text(error.message ?? "An error has occured")
                .bold()
                .font(.system(size: 18))
            Spacer()
            Button("Retry", action: onClickRetry)
                .buttonStyle(.borderedProminent)
        }
        .frame(height: 54)
        .padding(EdgeInsets(top: 8, leading: 16, bottom: 8, trailing: 16))
    }
}

struct PageErrorItem_Previews: PreviewProvider {
    static var previews: some View {
        PageErrorItem(
            error: KotlinThrowable(message: "An error has occured"),
            onClickRetry: {}
        )
    }
}
